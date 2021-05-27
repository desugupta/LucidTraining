package com.leave.employee.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Document(collection = "LeaveStatistics")
public class LeaveStatistics extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Version
	private Integer versionId;
	private Integer employeeId;
	// private String leaveBalance;// [sick,earned leave]
	private List<LeaveTypeBalance> leaveTypeBalance;
	private Integer accrusedThisYear;
	private Integer creditedFromLastYear;
	private Integer annualAllotment;

	// private String leaveRequest;// [planned,approved]
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

	public List<LeaveTypeBalance> getLeaveTypeBalance() {
		return leaveTypeBalance;
	}

	public void setLeaveTypeBalance(List<LeaveTypeBalance> leaveTypeBalance) {
		this.leaveTypeBalance = leaveTypeBalance;
	}

	public Integer getAccrusedThisYear() {
		return accrusedThisYear;
	}

	public void setAccrusedThisYear(Integer accrusedThisYear) {
		this.accrusedThisYear = accrusedThisYear;
	}

	public Integer getCreditedFromLastYear() {
		return creditedFromLastYear;
	}

	public void setCreditedFromLastYear(Integer creditedFromLastYear) {
		this.creditedFromLastYear = creditedFromLastYear;
	}

	public Integer getAnnualAllotment() {
		return annualAllotment;
	}

	public void setAnnualAllotment(Integer annualAllotment) {
		this.annualAllotment = annualAllotment;
	}

	@Override
	public String toString() {
		return "LeaveStatistics [id=" + id + ", versionId=" + versionId + ", employeeId=" + employeeId
				+ ", leaveTypeBalance=" + leaveTypeBalance + ", accrusedThisYear=" + accrusedThisYear
				+ ", creditedFromLastYear=" + creditedFromLastYear + ", annualAllotment=" + annualAllotment + "]";
	}

}
