package com.leave.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.employee_model.EmployeeModel;
import com.leave.employee_service.EmployeeService;

@RestController 
@RequestMapping(value = "/employee")
public class EmployeeController {
	public EmployeeModel employeeModel;
	
	@PostMapping(value = "/")
	public String addEmployee(@RequestBody EmployeeModel employeeModel) {
		return EmployeeService.addEmployee(employeeModel);
	}
	
	@PutMapping(value = "/{id}")
	public String updateEmployee(@PathVariable("id") int id, @PathVariable EmployeeModel employeeModel) {
		return EmployeeService.updateEmployee(id, employeeModel); 
	}
	
	@GetMapping(value = "/")
	public List<EmployeeModel> getAllEmployee() { 
		return EmployeeService.getAllEmployee();
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		return EmployeeService.deleteEmployee(id); 
	} 
	
	
}
