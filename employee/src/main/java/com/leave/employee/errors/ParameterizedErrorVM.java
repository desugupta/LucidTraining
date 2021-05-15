package com.leave.employee.errors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * View Model for sending a parameterized error message.
 */
public class ParameterizedErrorVM implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String exception;
	private final String exceptionMessage;
	private final List<String> errorCodeList;
	private Map<String,List<String>> data;
	private final boolean success;

	public ParameterizedErrorVM(String exception, String message, List<String> errorCodeList) {
		this.success = false;
		this.exception = exception;
		this.exceptionMessage = message;
		this.errorCodeList = errorCodeList;
	}
	
	public ParameterizedErrorVM(String exception, String message,Map<String,List<String>> data,List<String> errorCodeList) {
		this.success = false;
		this.exception = exception;
		this.exceptionMessage = message;
		this.errorCodeList = errorCodeList;
		this.data = data;
	}
	
	public String getException() {
		return exception;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public List<String> getErrorCodeList() {
		return errorCodeList;
	}

	public boolean isSuccess() {
		return success;
	}

	public Map<String, List<String>> getData() {
		return data;
	}
	
}
