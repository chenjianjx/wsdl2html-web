package org.jaxws.wsdl2htmlweb.entry.webcontroller;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author shaunyip@outlook.com
 *
 */
public class WebControllerUtils {

	/**
	 * never change the spelling
	 */
	public static final String SESSION_KEY_USER_NAME = "sessionUserName";

	public static String getRequestUriWithoutContextPath(
			HttpServletRequest request) {
		String uri = request.getRequestURI();
		String cp = request.getContextPath();

		return removeContextPath(uri, cp);

	}

	private static String removeContextPath(String uri, String cp) {
		if (cp.equals("")) {
			return uri;
		}
		if (cp.equals("/")) {
			return uri;
		}
		return uri.substring(cp.length());
	}
	
 

}
