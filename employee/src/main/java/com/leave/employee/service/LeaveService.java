package com.leave.employee.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.leave.employee.domain.LeavesDetails;
import com.leave.employee.exception.LeaveIsRejectedException;
import com.leave.employee.exception.NoLeavesAvailableException;

public interface LeaveService {	

	public LeavesDetails applyLeave(LeavesDetails leavesDetails);

	public LeavesDetails updateLeave(LeavesDetails leavesDetails) throws NoLeavesAvailableException, LeaveIsRejectedException, ParseException;

	/**
	 * @author Rajasekhar.D
	 * @description to generate csv for insurance
	 */
	Boolean generateCSVForAttendance();

	public Boolean getAttendanceReport(Date startDate, Date endDate, Integer employeeId);

}
