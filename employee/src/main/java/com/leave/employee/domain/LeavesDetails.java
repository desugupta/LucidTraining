package com.leave.employee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LeaveDetails")
public class LeavesDetails extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Version
	private Integer versionId;
	private String employeeId;
	private Date approvalDate;
	private Date requestDate;
	private Date leaveStartDate;
	private Date leaveEndDate;
	private String leaveStatus;// (approved/rejected)
	private String leaveType; // (EL,SL,LOP)
	private String leaveDay;// (full or half)
	private String leaveReason;
	private String managerEmpId;
	private String department;
	private HolidaysList holidaysList;

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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(String leaveDay) {
		this.leaveDay = leaveDay;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getManagerEmpId() {
		return managerEmpId;
	}

	public void setManagerEmpId(String managerEmpId) {
		this.managerEmpId = managerEmpId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "LeavesDetails [id=" + id + ", versionId=" + versionId + ", employeeId=" + employeeId + ", approvalDate="
				+ approvalDate + ", requestDate=" + requestDate + ", leaveStartDate=" + leaveStartDate
				+ ", leaveEndDate=" + leaveEndDate + ", leaveStatus=" + leaveStatus + ", leaveType=" + leaveType
				+ ", leaveDay=" + leaveDay + ", leaveReason=" + leaveReason + ", managerEmpId=" + managerEmpId
				+ ", department=" + department + "]";
	}

}
