package com.leave.employee.util;

import com.leave.employee.domain.ResponseObject;

public class ResponseUtil {

	public static <T> ResponseObject<T> createSuccessResponse(T object) {
		ResponseObject<T> responseObject = new ResponseObject<T>();
		responseObject.setResult(object);
		responseObject.setSuccess(true);
		responseObject.setErrorMessage(null);
		return responseObject;
	}	

	public static <T> ResponseObject<T> createErrorResponse(String errorMessage) {
		ResponseObject<T> responseObject = new ResponseObject<T>();
		responseObject.setResult(null);
		responseObject.setSuccess(false);
		responseObject.setErrorMessage(errorMessage);
		return responseObject;
	}
}