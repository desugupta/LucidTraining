package com.leave.employee.errors;


/**
 * @author rajasekhar.d
 *
 */
public enum CustomExceptionCode {

    TEST("EHV001","TEST"),
    
	JSON_PARSE_EXCEPTION("EHV004","Exception occured while parsing java object");

	private final String errCode;
	private final String errMsg;

	private CustomExceptionCode(final String errCode, final String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	@Override
	public String toString() {
		return errCode + " : " + errMsg;
	}

}
