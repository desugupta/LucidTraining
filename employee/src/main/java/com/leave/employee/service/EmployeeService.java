package com.leave.employee.service;

import java.util.List;
import java.util.Optional;

import com.leave.employee.domain.EmployeeUser;

public interface EmployeeService {
	
	public EmployeeUser saveEmployee(EmployeeUser employeeUser);

	public List<EmployeeUser> getAllEmployees();

	public EmployeeUser getEmployee(String employeeId);

	public EmployeeUser updateEmployee(EmployeeUser employeeUser);

}
