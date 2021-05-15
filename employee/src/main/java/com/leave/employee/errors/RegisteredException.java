package com.leave.employee.errors;

/**
 * The enum which contains all the registered excpetions
 * 
 * @author rajasekhar.d
 *
 */
public enum RegisteredException {

	UNKNOWN_EXCEPTION("Unknown Exception", ""),
	METHOD_NOT_ALLOWED("HttpRequestMethodNotSupportedException","Http Request Method Not Supported Exception Occurred"),
	CONSTRAINT_VIOLATION_EXCEPTION("ConstraintViolationException", "Constraint Violation Exception Occurred"),
	UNAUTHORIZED_EXCEPTION("UnauthorizedException", "Unauthorized Exception Occurred"),
	RESPONSE_PARSER_EXCEPTION("JsonParsingException", "Json Parsing exception Occurred"),
	DATE_RANGE_INVALID_EXCEPTION("DateRangeInvalidException", "Provided start date should be before the provided end date ");

	private final String exception;

	private final String exceptionMessage;

	private RegisteredException(final String exception, String exceptionMessage) {
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
	}

	public String getException() {
		return exception;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
