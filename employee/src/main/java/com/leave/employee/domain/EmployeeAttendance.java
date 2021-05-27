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
	private Integer employeeId;
	private Date normalDate;
	private String timeIn;
	private String timeOut;
	private String workingHours;
	private float working;

	public float getWorking() {
		return working;
	}

	public void setWorking(float working) {
		this.working = working;
	}

	// private String month;
	private String location;// office
	// private long attencount;

	public String getId() {
		return id;
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

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Date getNormalDate() {
		return normalDate;
	}

	public void setNormalDate(Date normalDate) {
		this.normalDate = normalDate;
	}

	public String getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "EmployeeAttendance [id=" + id + ", versionId=" + versionId + ", employeeId=" + employeeId
				+ ", normalDate=" + normalDate + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", workingHours="
				+ workingHours + ", location=" + location + "]";
	}
}
