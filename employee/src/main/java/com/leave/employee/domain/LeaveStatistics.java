package com.leave.employee.domain;

import java.util.List;
import javax.validation.constraints.NotNull;
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
public class LeaveStatistics {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Version
	private Integer versionId;
	
	@NotNull(message = "should not be empty or null")
	private Integer employeeId;
	
	@NotNull(message = "should not be empty or null")
	private List<LeaveTypeBalance> leaveTypeBalance;
	private Integer annualAllotment;

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

	public Integer getAnnualAllotment() {
		return annualAllotment;
	}

	public void setAnnualAllotment(Integer annualAllotment) {
		this.annualAllotment = annualAllotment;
	}

	@Override
	public String toString() {
		return "LeaveStatistics [id=" + id + ", versionId=" + versionId + ", employeeId=" + employeeId
				+ ", leaveTypeBalance=" + leaveTypeBalance + ", annualAllotment=" + annualAllotment + "]";
	}

}
