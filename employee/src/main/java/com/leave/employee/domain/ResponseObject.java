package com.leave.employee.domain;

public class ResponseObject<T> {

	private T result;
	private boolean isSuccess;
	private String errorMessage;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ResultObject [result=" + result + ", isSuccess=" + isSuccess + ", errorMessage=" + errorMessage + "]";
	}
}