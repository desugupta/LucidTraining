package com.leave.employee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EmployeeAttendance")
public class EmployeeAttendance extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Version
	private Integer versionId;
	private String employeeId;
	private Date normalDate;
	private Date timeIn;
	private Date timeOut;
	private int workingHours;
	private String month;
	private String location;// office
	// private long attencount;

	public String getId() {
		return id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getNormalDate() {
		return normalDate;
	}

	public void setNormalDate(Date normalDate) {
		this.normalDate = normalDate;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	@Override
	public String toString() {
		return "EmployeeAttendance [id=" + id + ", versionId=" + versionId + ", employeeId=" + employeeId
				+ ", normalDate=" + normalDate + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", workingHours="
				+ workingHours + ", month=" + month + ", location=" + location + "]";
	}

}
