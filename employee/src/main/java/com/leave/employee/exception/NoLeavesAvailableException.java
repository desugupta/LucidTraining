package com.leave.employee.exception;

@SuppressWarnings("serial")
public class NoLeavesAvailableException extends Exception {
	
	public NoLeavesAvailableException(String s) {
		super(s);
	}
	
}