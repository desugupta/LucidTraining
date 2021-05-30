package com.leave.employee.domain;

import java.time.LocalDate;
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
	private Integer employeeId;
	private LocalDate approvalDate;
	private LocalDate requestDate;
	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	private String leaveStatus;// (approved/rejected)
	private String leaveType; // (EL,SL,LOP)
	private String leaveDay;// (full or half)
	private String leaveReason;
	private Integer managerEmpId;
	private String department;

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

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
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

	public Integer getManagerEmpId() {
		return managerEmpId;
	}

	public void setManagerEmpId(Integer managerEmpId) {
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