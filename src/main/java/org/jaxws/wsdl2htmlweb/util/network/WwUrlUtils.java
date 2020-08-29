package org.jaxws.wsdl2htmlweb.util.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WwUrlUtils {

	
	public static String encode(String url){
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
}
