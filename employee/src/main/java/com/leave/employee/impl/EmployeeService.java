package com.leave.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.employee_model.EmployeeModel;
import com.leave.employee_repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	public static EmployeeRepository employeeRepository;
	public static EmployeeModel employeeDetails;

	public static String addEmployee(EmployeeModel employeeModel) {
		employeeDetails.setEmployee_id(employeeModel.getEmployee_id());
		employeeDetails.setAge(employeeModel.getAge());
		employeeDetails.setBloodGroup(employeeModel.getBloodGroup());
		employeeDetails.setCity(employeeModel.getCity());
		employeeDetails.setCountry(employeeModel.getCountry());
		employeeDetails.setDepartment(employeeModel.getDepartment());
		employeeDetails.setDesignation(employeeModel.getDesignation());
		employeeDetails.setDob(employeeModel.getDob());
		employeeDetails.setEmailId(employeeModel.getEmailId());
		employeeDetails.setEmployeeStatus(employeeModel.getEmployeeStatus());
		employeeDetails.setEndDate(employeeModel.getEndDate());
		employeeDetails.setFirstName(employeeModel.getFirstName());
		employeeDetails.setGender(employeeModel.getGender());
		employeeDetails.setJoinDate(employeeModel.getJoinDate());
		employeeDetails.setLastName(employeeModel.getLastName());
		employeeDetails.setMobileNo(employeeModel.getMobileNo());
		employeeDetails.setPermenantAddress(employeeModel.getPermenantAddress());
		employeeDetails.setPincode(employeeModel.getPincode());
		employeeDetails.setRole(employeeModel.getRole());
		employeeDetails = employeeRepository.insert(employeeDetails);	
		return "Employee Details sucessfully saved";
	}

	public static List<EmployeeModel> getAllEmployee() {
		return employeeRepository.findAll(); 
	}

	public static String updateEmployee(int id, EmployeeModel employeeModel) {
		employeeDetails.setEmployee_id(employeeModel.getEmployee_id());
		employeeDetails.setAge(employeeModel.getAge());
		employeeDetails.setBloodGroup(employeeModel.getBloodGroup());
		employeeDetails.setCity(employeeModel.getCity());
		employeeDetails.setCountry(employeeModel.getCountry());
		employeeDetails.setDepartment(employeeModel.getDepartment());
		employeeDetails.setDesignation(employeeModel.getDesignation());
		employeeDetails.setDob(employeeModel.getDob());
		employeeDetails.setEmailId(employeeModel.getEmailId());
		employeeDetails.setEmployeeStatus(employeeModel.getEmployeeStatus());
		employeeDetails.setEndDate(employeeModel.getEndDate());
		employeeDetails.setFirstName(employeeModel.getFirstName());
		employeeDetails.setGender(employeeModel.getGender());
		employeeDetails.setJoinDate(employeeModel.getJoinDate());
		employeeDetails.setLastName(employeeModel.getLastName());
		employeeDetails.setMobileNo(employeeModel.getMobileNo());
		employeeDetails.setPermenantAddress(employeeModel.getPermenantAddress());
		employeeDetails.setPincode(employeeModel.getPincode());
		employeeDetails.setRole(employeeModel.getRole());
		employeeDetails = employeeRepository.insert(employeeDetails);	
		deleteEmployeeParmanently(id);
		return "Employee Details successfully updated";
	}

	private static void deleteEmployeeParmanently(int id) {
		employeeRepository.deleteById(id);
	}

	public static String deleteEmployee(int id) {
		employeeDetails = employeeRepository.findById(id);
		employeeDetails.setEmployeeStatus("inactive");
		return "Successfully changed status";
	}

}
