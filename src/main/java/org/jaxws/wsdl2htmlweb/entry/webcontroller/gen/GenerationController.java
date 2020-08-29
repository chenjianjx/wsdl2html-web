package org.jaxws.wsdl2htmlweb.entry.webcontroller.gen;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jaxws.wsdl2bytecodes.service.WsdlImportException;
import org.jaxws.wsdl2html.service.Wsdl2Html;
import org.jaxws.wsdl2htmlweb.infrahelper.spring.mvc.WwError;
import org.jaxws.wsdl2htmlweb.infrahelper.spring.mvc.WwModelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;


@Controller
@RequestMapping(value = "/")
public class GenerationController {


    @RequestMapping(value = {"", "/"})
    public String form(HttpServletRequest request, ModelMap model) {
        GenerationCommand command = new GenerationCommand();
        String requestUrlBase = request.getRequestURL().toString();
        String defaultWsdl = StringUtils.stripEnd(requestUrlBase, "/") + "/assets/static/sample.wsdl";
        command.setDefaultWsdl(defaultWsdl);

        if (StringUtils.isBlank(command.getWsdlUrl())) {
            command.setWsdlUrl(defaultWsdl);
        }

        model.addAttribute("command", command);
        return "/gen/form";
    }

    @RequestMapping(value = "/doGen", method = RequestMethod.POST)
    public String submitForm(ModelMap model,
                             @ModelAttribute("command") GenerationCommand command) {

        String formPage = "/gen/form";

        if (command.getWsdlUrl() == null) {
            WwModelUtils.addError(model, "Please input a wsdl url.");
            return formPage;
        }

        try {
            new URL(command.getWsdlUrl());
        } catch (MalformedURLException e) {
            WwModelUtils.addError(model, "Please input a valid wsdl url.");
            return formPage;
        }

        String htmlContent;
        try {
            htmlContent = Wsdl2Html.generateHtml(command.getWsdlUrl());
            model.put("result", htmlContent);
        } catch (WsdlImportException e) {
            WwModelUtils.addError(model, new WwError("Conversion failed because ", e.getMessage()));
            return formPage;
        }

        return "/gen/result";

    }

    public static final class GenerationCommand {

        private String wsdlUrl;
        private String defaultWsdl;

        public String getWsdlUrl() {
            return wsdlUrl;
        }

        public void setWsdlUrl(String wsdlUrl) {
            this.wsdlUrl = wsdlUrl;
        }

        public String getDefaultWsdl() {
            return defaultWsdl;
        }

        public void setDefaultWsdl(String defaultWsdl) {
            this.defaultWsdl = defaultWsdl;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this,
                    ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }


}
