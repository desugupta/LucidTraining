package com.leave.employee.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.domain.Role;
import com.leave.employee.repository.EmployeeRepository;
import com.leave.employee.service.EmployeeService;

@Service
public class EmployeeImpl implements EmployeeService{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	public static final String SEQUENCE_NAME="user_sequence";
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public EmployeeUser saveEmployee(EmployeeUser employeeUser) {
		//employeeUser.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
		return employeeRepository.save(employeeUser);
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
	public EmployeeUser getEmployee(String employeeId) {
			try {
				EmployeeUser employeeUser = null;
				if (employeeId != null && !employeeId.isEmpty()) {
					employeeUser = employeeRepository.findByEmployeeId(employeeId);
				}
				return employeeUser;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error occurred while getting the employeeInfo:", e);
				throw e;
			}
}



{ EmployeeUser empUser=new EmployeeUser(); if()
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
  employeeDetails.setRole(employeeModel.getRole()); return null; }

	private String emailId;
	private int age;
	private String gender;
	private String mobileNo;
	private Date dob;
	private List<Role> roles;
	private String department;
	private String designation;
	private String country;
	private String city;
	private int pincode;
	private String employeeStatus;
	private String permanentAddress;
	private String bloodGroup;
	private Date joinDate;
	private Date endDate;
	private String managerEmpId;

	@Override
	public EmployeeUser updateEmployee(EmployeeUser employeeModel) {
		try {
			EmployeeUser employeeDetails = employeeRepository.findByEmployeeId(employeeModel.getEmployeeId());  
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getLastName()!=null && !employeeDetails.getLastName().isEmpty()) {
				  employeeDetails.setLastName(employeeModel.getLastName());
			}
			if(employeeDetails.getEmailId()!=null && !employeeDetails.getEmailId().isEmpty()) {
				  employeeDetails.setEmailId(employeeModel.getEmailId());
			}
			if(employeeDetails.getAge()) {
				  employeeDetails.setAge(employeeModel.getAge());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			if(employeeDetails.getFirstName()!=null && !employeeDetails.getFirstName().isEmpty()) {
				  employeeDetails.setFirstName(employeeModel.getFirstName());
			}
			
			
			
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
			  employeeDetails.setGender(employeeModel.getGender());
			  employeeDetails.setJoinDate(employeeModel.getJoinDate());
			  employeeDetails.setLastName(employeeModel.getLastName());
			  employeeDetails.setMobileNo(employeeModel.getMobileNo());
			  employeeDetails.setPermenantAddress(employeeModel.getPermenantAddress());
			  employeeDetails.setPincode(employeeModel.getPincode());
			  employeeDetails.setRole(employeeModel.getRole());
			employeeDetails = employeeRepository.save(employeeDetails);
			return employeeDetails;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while updating the employee:", e);
			throw e;
		}
	}

	
	
}
