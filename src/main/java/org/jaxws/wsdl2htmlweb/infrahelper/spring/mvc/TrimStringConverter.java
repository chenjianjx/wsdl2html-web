package org.jaxws.wsdl2htmlweb.infrahelper.spring.mvc;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class TrimStringConverter implements Converter<String, String> {

	public String convert(String s) {
		return StringUtils.trimToNull(s);
	}

}