package org.jaxws.wsdl2htmlweb.util.lang;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

 
public class WwDateUtils {
 
 
	public static final Date parseDate(String dateStr, String format) {
		try {
			return DateUtils.parseDate(dateStr, new String[] { format });
		} catch (ParseException e) {
			return null;

		}

	}

}
