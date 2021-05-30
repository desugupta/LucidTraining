package com.leave.employee.domain;

public class LeaveTypeBalance {

	private Integer leaveId;
	private String leaveType;
	private double noOfDays;

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public double getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(double noOfDays) {
		this.noOfDays = noOfDays;
	}

	@Override
	public String toString() {
		return "LeaveTypeBalance [leaveId=" + leaveId + ", leaveType=" + leaveType + ", noOfDays=" + noOfDays + "]";
	}

}
