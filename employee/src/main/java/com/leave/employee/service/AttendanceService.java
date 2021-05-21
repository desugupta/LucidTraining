package com.leave.employee.service;

import java.text.ParseException;

import com.leave.employee.domain.EmployeeAttendance;

public interface AttendanceService {
	
public EmployeeAttendance saveAttendance(EmployeeAttendance employeeAttendance) throws ParseException;




}