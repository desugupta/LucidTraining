package com.leave.employee.service;

import com.leave.employee.domain.EmployeeAttendance;
import com.leave.employee.domain.LeavesDetails;

public interface LeaveService {

	public LeavesDetails applyLeave(EmployeeAttendance attendance);
	

}
