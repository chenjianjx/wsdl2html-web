package org.jaxws.wsdl2htmlweb.util.lang;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class WwBaseResponse {

	private boolean success;
	private List<Error> errors;

	public WwBaseResponse() {

	}

	public static <T extends WwBaseResponse> T successResponse(Class<T> responseClass) {
		T response = newInstance(responseClass);
		response.setSuccess(true);
		return response;
	}

	public static <T extends WwBaseResponse> T failureResponse(Class<T> responseClass,
			String errorCode, String errorMsg) {
		T response = newInstance(responseClass);
		response.setSuccess(false);
		response.addError(errorCode, errorMsg);
		return response;
	}

	public static <T extends WwBaseResponse> T failureResponse(Class<T> responseClass, String errorCode) {
		return failureResponse(responseClass, errorCode, null);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Error> getErrors() {
		if (errors == null) {
			this.errors = new ArrayList<Error>();
		}
		return errors;
	}

	public void addError(String code, String msg) {
		Error e = new Error();
		e.setCode(code);
		e.setMsg(msg);
		this.getErrors().add(e);
	}

	public void addErrors(List<Error> errors) {
		this.getErrors().addAll(errors);
	}

	public static final class Error {
		private String code;
		/**
		 * could be null
		 */
		private String msg;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this,
					ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	private static <T extends WwBaseResponse> T newInstance(Class<T> responseClass) {
		try {
			return responseClass.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
