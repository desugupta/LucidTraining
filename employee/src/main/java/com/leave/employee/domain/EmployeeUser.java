package com.leave.employee.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
	
@Document(collection = "Employee")
public class EmployeeUser extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";

	@Id
	private String id;
	@Version
	private Integer versionId;
	
	@NotEmpty(message = "userName should not be empty")
	private String userName;
	private String password;
	private Integer employeeId;
	private String firstName;
	private String lastName;
	@NotEmpty(message = "emailId should not be empty")
	private String emailId;
	private Integer age;
	private String gender;
	private String mobileNo;
	private Date dob;   	
	private Set<Role> roles;
	private String department;
	private String designation;
	private String country;
	private String city;
	private String pincode;
	private String employeeStatus;
	private String permanentAddress;
	private String bloodGroup;
	private Date joinDate;
	private Date endDate;
	private Integer managerEmpId;
	private String managerEmailId;

	public EmployeeUser(String userName, String password, String firstName) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
	}


	public EmployeeUser() {
		// TODO Auto-generated constructor stub
	}


	


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getVersionId() {
		return versionId;
	}


	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getEmployeeStatus() {
		return employeeStatus;
	}


	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}


	public String getPermanentAddress() {
		return permanentAddress;
	}


	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}


	public String getBloodGroup() {
		return bloodGroup;
	}


	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getManagerEmpId() {
		return managerEmpId;
	}


	public void setManagerEmpId(Integer managerEmpId) {
		this.managerEmpId = managerEmpId;
	}


	public String getManagerEmailId() {
		return managerEmailId;
	}


	public void setManagerEmailId(String managerEmailId) {
		this.managerEmailId = managerEmailId;
	}

	@Override
	public String toString() {
		return "EmployeeUser [id=" + id + ", versionId=" + versionId + ", userName=" + userName + ", password="
				+ password + ", employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", age=" + age + ", gender=" + gender + ", mobileNo=" + mobileNo + ", dob="
				+ dob + ", roles=" + roles + ", department=" + department + ", designation=" + designation
				+ ", country=" + country + ", city=" + city + ", pincode=" + pincode + ", employeeStatus="
				+ employeeStatus + ", permanentAddress=" + permanentAddress + ", bloodGroup=" + bloodGroup
				+ ", joinDate=" + joinDate + ", endDate=" + endDate + ", managerEmpId=" + managerEmpId
				+ ", managerEmailId=" + managerEmailId + "]";
	}


	public Object thenReturn(List<EmployeeUser> collect) {
		// TODO Auto-generated method stub
		return null;
	}

}
