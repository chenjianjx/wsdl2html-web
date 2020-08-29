package org.jaxws.wsdl2htmlweb.entry.webcontroller.gen;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Controller
@RequestMapping(value = "/")
public class GenerationController {
	
	

	@RequestMapping(value = { "", "/" })
	public String form(ModelMap model) {
		GenerationCommand command = new GenerationCommand();
		model.addAttribute("command", command);
		return "/gen/form";
	}

	@RequestMapping(value = "/doGen", method = RequestMethod.POST)
	public String submitForm(HttpServletRequest request, ModelMap model,
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

		private static final String DEFAULT_WSDL = "http://www.wsdl2html.com/assets/static/sample.wsdl";
		private String wsdlUrl = DEFAULT_WSDL;

		public String getWsdlUrl() {
			return wsdlUrl;
		}

		public void setWsdlUrl(String wsdlUrl) {
			this.wsdlUrl = wsdlUrl;
		}
		
		public String getDefaultWsdl(){
			return DEFAULT_WSDL;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this,
					ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	@SuppressWarnings("unused")
	private String toJson(Object object) {
		return JSON.toJSONString(object,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.BrowserCompatible);
	}

}
