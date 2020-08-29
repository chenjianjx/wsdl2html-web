package org.jaxws.wsdl2htmlweb.infrahelper.spring.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;

public class WwModelUtils {
	private static final String ERRORS_KEY = "errors";

	public static void addError(ModelMap model, WwError error) {

		List<WwError> errors = getOrBuildErrors(model);
		errors.add(error);
	}

	public static void addError(ModelMap model, String msg) {

		List<WwError> errors = getOrBuildErrors(model);
		errors.add(new WwError(msg));
	}

	public static List<WwError> getErrors(ModelMap model) {

		List<WwError> errors = getOrBuildErrors(model);
		return errors;
	}

	private static List<WwError> getOrBuildErrors(ModelMap model) {
		@SuppressWarnings("unchecked")
		List<WwError> errors = (List<WwError>) model.get(ERRORS_KEY);
		if (errors == null) {
			errors = new ArrayList<WwError>();
			model.put(ERRORS_KEY, errors);
		}
		return errors;
	}

}
