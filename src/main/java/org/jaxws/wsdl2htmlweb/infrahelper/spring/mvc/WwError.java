package org.jaxws.wsdl2htmlweb.infrahelper.spring.mvc;

/**
 * 
 * @author chenjianjx
 *
 */
public class WwError {
	private String msg;
	/**
	 * Will be rendered in
	 * 
	 * <pre> or something that can show line breaks;
	 */
	private String detail;

	public WwError(String msg, String detail) {
		super();
		this.msg = msg;
		this.detail = detail;
	}

	public WwError(String msg) {
		super();
		this.msg = msg;
	}

	public WwError() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return msg;
	}
}
