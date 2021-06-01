package com.leave.employee.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EmployeeAttendance")
public class EmployeeAttendance {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Version
	private Integer versionId;
	private Integer sno;
	@NotNull(message = "should not be empty or null")
	private Integer employeeId;
	@NotNull(message = "should not be empty or null")
	private Date normalDate;
	@NotNull(message = "should not be empty or null")	
	private String timeIn;
	@NotNull(message = "should not be empty or null")
	private String timeOut;
	private float working;
	private String location;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public float getWorking() {
		return working;
	}

	public void setWorking(float working) {
		this.working = working;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "EmployeeAttendance [id=" + id + ", versionId=" + versionId + ", sno=" + sno + ", employeeId="
				+ employeeId + ", normalDate=" + normalDate + ", timeIn=" + timeIn + ", timeOut=" + timeOut
				+ ", working=" + working + ", location=" + location + "]";
	}

}
