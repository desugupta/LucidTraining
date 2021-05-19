package com.leave.employee.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LeaveStatistics")
public class LeaveStatistics extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Version
	private Integer versionId;
	private String employeeId;
	private String leaveBalance;// [sick,earned leave]
	private String accrusedThisYear;
	private String creditedFromLastYear;
	private String annualAllotment;
	//private String leaveRequest;// [planned,approved]	
	
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
	public String getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(String leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public String getAccrusedThisYear() {
		return accrusedThisYear;
	}
	public void setAccrusedThisYear(String accrusedThisYear) {
		this.accrusedThisYear = accrusedThisYear;
	}
	public String getCreditedFromLastYear() {
		return creditedFromLastYear;
	}
	public void setCreditedFromLastYear(String creditedFromLastYear) {
		this.creditedFromLastYear = creditedFromLastYear;
	}
	public String getAnnualAllotment() {
		return annualAllotment;
	}
	public void setAnnualAllotment(String annualAllotment) {
		this.annualAllotment = annualAllotment;
	}
	@Override
	public String toString() {
		return "LeaveStatistics [id=" + id + ", versionId=" + versionId + ", employeeId=" + employeeId
				+ ", leaveBalance=" + leaveBalance + ", accrusedThisYear=" + accrusedThisYear
				+ ", creditedFromLastYear=" + creditedFromLastYear + ", annualAllotment=" + annualAllotment + "]";
	}
	

}
