package com.leave.employee.model;

public class AttendanceCsvResponse {

	private String sno;
	private String employeeId;
	private String working;
	private String startDate;
	private String endDate;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getWorking() {
		return working;
	}

	public void setWorking(String working) {
		this.working = working;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "AttendanceCsvResponse [sno=" + sno + ", employeeId=" + employeeId + ", working=" + working
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
