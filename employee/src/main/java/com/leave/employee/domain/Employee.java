package com.leave.employee.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee extends BaseDomain {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Version
	private Integer versionId;
	private String employeeId;
	private String employeeName;
	private String firstName;
	private String lastName;
	
	private String age;
	private String gender;
	private String mobileNo;
	private String emailId;
	private String dob;
	private List<Role> role;
	private boolean employeeOfferstatus;
	
	
	
	/*
	 * 
	 * 
	 * id(mongoId) employeeId employeeName firstName lastName age gender emailId
	 * mobile dob role(employee,admin,manager)Set(Role) department: prod dev
	 * designation: soft eng country city employeeStatus(active/inactive)
	 * supervisor(manager) password address pinCode joiningDate bloodGroup lastDate
	 */


	
	
	
	
	
	
	
	
	
	
	

}
