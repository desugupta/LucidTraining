package com.leave.employee.service;

import java.text.ParseException;
import java.util.Date;

import com.leave.employee.domain.LeaveStatistics;
import com.leave.employee.domain.LeavesDetails;
import com.leave.employee.exception.LeaveIsRejectedException;
import com.leave.employee.exception.NoLeavesAvailableException;
	
public interface LeaveService {	

	public LeavesDetails applyLeave(LeavesDetails leavesDetails);

	public LeavesDetails updateLeave(LeavesDetails leavesDetails) throws NoLeavesAvailableException, LeaveIsRejectedException, ParseException;

	public Boolean getAttendanceReport(Date startDate, Date endDate, Integer employeeId);

	public LeaveStatistics saveLeaveStatistics(LeaveStatistics leaveStatistics);

}
 