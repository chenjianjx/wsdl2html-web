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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


@Controller
@RequestMapping(value = "/")
public class GenerationController {


    @RequestMapping(value = {"", "/"})
    public String form(HttpServletRequest request, ModelMap model) {
        GenerationCommand command = new GenerationCommand();
        model.addAttribute("command", command);
        if(isRunningInsideDocker()){
            command.setInputPlaceholder(command.getInputPlaceholder() + " ('localhost' will be your host rather than the container)");
        }
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
            String wsdlUrl = command.getWsdlUrl();

            if (isRunningInsideDocker()) {
                wsdlUrl = rewriteUrlIfRunningInsideDocker(wsdlUrl);
            }

            htmlContent = Wsdl2Html.generateHtml(wsdlUrl);
            model.put("result", htmlContent);
        } catch (WsdlImportException e) {
            WwModelUtils.addError(model, new WwError("Conversion failed because ", e.getMessage()));
            return formPage;
        }

        return "/gen/result";

    }


    public static final class GenerationCommand {

        private String wsdlUrl;

        private String inputPlaceholder = "Input a wsdl url";

        public String getInputPlaceholder() {
            return inputPlaceholder;
        }

        public void setInputPlaceholder(String inputPlaceholder) {
            this.inputPlaceholder = inputPlaceholder;
        }

        public String getWsdlUrl() {
            return wsdlUrl;
        }

        public void setWsdlUrl(String wsdlUrl) {
            this.wsdlUrl = wsdlUrl;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this,
                    ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

    private static boolean isRunningInsideDocker() {

        try (Stream<String> stream =
                     Files.lines(Paths.get("/proc/1/cgroup"))) {
            return stream.anyMatch(line -> line.contains("/docker"));
        } catch (IOException e) {
            return false;
        }
    }

    String rewriteUrlIfRunningInsideDocker(String wsdlUrl) {
        if (wsdlUrl.toLowerCase().endsWith("//localhost")) {
            return StringUtils.replace(wsdlUrl, "//localhost", "//dockerhost"); //TODO: make this handled by regex
        }


        Pattern p = Pattern.compile("(\\/\\/)(localhost)([/:])", Pattern.CASE_INSENSITIVE);
        Matcher m = p
                .matcher(wsdlUrl);
        StringBuffer newMsg = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(newMsg, "$1dockerhost$3");
        }
        m.appendTail(newMsg);
        return newMsg.toString();
    }

}
