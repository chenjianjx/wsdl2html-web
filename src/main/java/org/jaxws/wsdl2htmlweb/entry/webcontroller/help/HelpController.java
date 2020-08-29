package org.jaxws.wsdl2htmlweb.entry.webcontroller.help;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/help")
public class HelpController {

	@RequestMapping(value = { "/faq" })
	public String faq(ModelMap model) {
		return "/help/faq";
	}

}
