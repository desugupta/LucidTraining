package com.leave.employee.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.repository.EmployeeRepository;
import com.leave.employee.service.EmployeeService;

@Service
public class EmployeeImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public static final String SEQUENCE_NAME = "user_sequence";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public EmployeeUser saveEmployee(EmployeeUser employeeUser) {
		try {
			/*
			 * // EmployeeUser employeeDetails =
			 * employeeRepository.findByEmployeeId(employeeUser.getEmployeeId()); String
			 * phoneNumber = employeeUser.getMobileNo(); if (!phoneNumber.startsWith("+91"))
			 * { phoneNumber = "+91" + phoneNumber; }
			 * employeeDetails.setFirstName(employeeUser.getFirstName());
			 * employeeDetails.setLastName(employeeUser.getLastName());
			 * employeeDetails.setEmailId(employeeUser.getEmailId());
			 * employeeDetails.setAge(employeeUser.getAge());
			 * employeeDetails.setGender(employeeUser.getGender());
			 * employeeDetails.setMobileNo(phoneNumber);
			 * employeeDetails.setDob(employeeUser.getDob());
			 * employeeDetails.setRoles(employeeUser.getRoles());
			 * employeeDetails.setDepartment(employeeUser.getDepartment());
			 * employeeDetails.setDesignation(employeeUser.getDesignation());
			 * employeeDetails.setCountry(employeeUser.getCountry());
			 * employeeDetails.setCity(employeeUser.getCity());
			 * employeeDetails.setPincode(employeeUser.getPincode());
			 * employeeDetails.setEmployeeStatus(employeeUser.getEmployeeStatus());
			 * employeeDetails.setPermanentAddress(employeeUser.getPermanentAddress());
			 * employeeDetails.setBloodGroup(employeeUser.getBloodGroup());
			 * employeeDetails.setJoinDate(employeeUser.getJoinDate());
			 * employeeDetails.setEndDate(employeeUser.getEndDate());
			 * employeeDetails.setManagerEmpId(employeeUser.getManagerEmpId());
			 */
			
			return employeeRepository.save(employeeUser);
		} catch (Exception e) {
			logger.error("Error occurred while saving an employee:", e);
			throw e;
		}
	}

	@Override
	public List<EmployeeUser> getAllEmployees() {
		try {
			List<EmployeeUser> employees = employeeRepository.findAll();
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while getting all employee infoList:", e);
			throw e;
		}
	}

	@Override
	public EmployeeUser getEmployee(int employeeId) {
		try {
			EmployeeUser employeeUser = null;
			employeeUser = employeeRepository.findByEmployeeId(employeeId);
			return employeeUser;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while getting the employeeInfo:", e);
			throw e;
		}
	}

	@Override
	public EmployeeUser updateEmployee(EmployeeUser employeeModel) {
		try {
			EmployeeUser employeeDetails = employeeRepository.findByEmployeeId(employeeModel.getEmployeeId());
			if (employeeDetails.getEmployeeStatus() != null && !employeeDetails.getEmployeeStatus().isEmpty()) {
				employeeDetails.setUserName(employeeModel.getUserName());
			}
			if (employeeDetails.getFirstName() != null && !employeeDetails.getFirstName().isEmpty()) {
				employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if (employeeDetails.getLastName() != null && !employeeDetails.getLastName().isEmpty()) {
				employeeDetails.setLastName(employeeModel.getLastName());
			}
			if (employeeDetails.getEmailId() != null && !employeeDetails.getEmailId().isEmpty()) {
				employeeDetails.setEmailId(employeeModel.getEmailId());
			}
			if (employeeDetails.getAge() != null) {
				employeeDetails.setAge(employeeModel.getAge());
			}
			if (employeeDetails.getGender() != null && !employeeDetails.getGender().isEmpty()) {
				employeeDetails.setGender(employeeModel.getGender());
			}
			if (employeeDetails.getMobileNo() != null && !employeeDetails.getMobileNo().isEmpty()) {
				String phoneNumber = employeeModel.getMobileNo();
				if (!phoneNumber.startsWith("+91")) {
					phoneNumber = "+91" + phoneNumber;
				}
				employeeDetails.setMobileNo(phoneNumber);
			}
			if (employeeDetails.getDepartment() != null && !employeeDetails.getDepartment().isEmpty()) {
				employeeDetails.setDepartment(employeeModel.getDepartment());
			}
			if (employeeDetails.getDesignation() != null && !employeeDetails.getDesignation().isEmpty()) {
				employeeDetails.setDesignation(employeeModel.getDesignation());
			}
			if (employeeDetails.getCountry() != null && !employeeDetails.getCountry().isEmpty()) {
				employeeDetails.setCountry(employeeModel.getCountry());
			}
			if (employeeDetails.getRoles() != null && !employeeDetails.getRoles().isEmpty()) {
				employeeDetails.setRoles(employeeModel.getRoles());
			}
			if (employeeDetails.getCity() != null && !employeeDetails.getCity().isEmpty()) {
				employeeDetails.setCity(employeeModel.getCity());
			}
			if (employeeDetails.getPincode() != null && !employeeDetails.getPincode().isEmpty()) {
				employeeDetails.setPincode(employeeModel.getPincode());
			}
			if (employeeDetails.getEmployeeStatus() != null && !employeeDetails.getEmployeeStatus().isEmpty()) {
				employeeDetails.setEmployeeStatus(employeeModel.getEmployeeStatus());
			}
			if (employeeDetails.getPermanentAddress() != null && !employeeDetails.getPermanentAddress().isEmpty()) {
				employeeDetails.setPermanentAddress(employeeModel.getPermanentAddress());
			}
			if (employeeDetails.getBloodGroup() != null && !employeeDetails.getBloodGroup().isEmpty()) {
				employeeDetails.setBloodGroup(employeeModel.getBloodGroup());
			}
			if (employeeDetails.getManagerEmpId() != null) {
				employeeDetails.setManagerEmpId(employeeModel.getManagerEmpId());
			}
			employeeDetails = employeeRepository.save(employeeDetails);
			employeeDetails = modelMapper.map(employeeDetails, EmployeeUser.class);
			return employeeDetails;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while updating the employee:", e);
			throw e;
		}
	}

}
