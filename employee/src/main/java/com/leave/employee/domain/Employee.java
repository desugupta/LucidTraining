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
	private String password;
	private String emailId;
	private String age;
	private String gender;
	private String mobileNo;
	private String dob;
	private List<Role> role;
	private boolean employeeOfferstatus;
	
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	public boolean isEmployeeOfferstatus() {
		return employeeOfferstatus;
	}
	public void setEmployeeOfferstatus(boolean employeeOfferstatus) {
		this.employeeOfferstatus = employeeOfferstatus;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", versionId=" + versionId + ", employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", emailId=" + emailId + ", age=" + age + ", gender=" + gender + ", mobileNo=" + mobileNo + ", dob="
				+ dob + ", role=" + role + ", employeeOfferstatus=" + employeeOfferstatus + "]";
	}
	

}
