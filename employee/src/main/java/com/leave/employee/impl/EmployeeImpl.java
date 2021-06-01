package com.leave.employee.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.exception.EmployeeNotFoundException;
import com.leave.employee.repository.EmployeeRepository;
import com.leave.employee.service.EmployeeService;
import com.leave.employee.util.StringsUtil;	

@Service
public class EmployeeImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public EmployeeUser saveEmployee(EmployeeUser employeeUser) {
		try {
			return employeeRepository.save(employeeUser);
		} catch (Exception e) {
			logger.error("Error occurred while saving an employee:", e);
			throw e;
		}
	}

	/**
	 * @author rajasekhar.d
	 * @description to get the all employees
	 */
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

	/**
	 * @author rajasekhar.d
	 * @description to get the employee by employeeId
	 */
	@Override
	public EmployeeUser getEmployee(int employeeId) throws Exception {
		try {
			EmployeeUser employeeUser = null;
			employeeUser = employeeRepository.findByEmployeeId(employeeId);
			if (employeeUser == null) {
				throw new EmployeeNotFoundException("Employee does not exists in the database");
			}
			return employeeUser;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while getting the employeeInfo:", e);
			throw e;
		}
	}

	/**
	 * @author rajasekhar.d
	 * @description to update the employee
	 * @param employeeModel
	 */
	@Override
	public EmployeeUser updateEmployee(EmployeeUser employeeModel) throws Exception {
		try {
			EmployeeUser employeeDetails = employeeRepository.findByEmployeeId(employeeModel.getEmployeeId());
			if (employeeDetails == null) {
				throw new EmployeeNotFoundException("Employee does not exists in the database");
			}
			if (!StringsUtil.isNullOrEmpty(employeeModel.getUserName())) {
				employeeDetails.setUserName(employeeModel.getUserName());
			}
			if (employeeModel.getEmailId() != null && !employeeModel.getEmailId().isEmpty()) {
				employeeDetails.setEmailId(employeeModel.getEmailId());
			}
			if (employeeModel.getMobileNo() != null && !employeeModel.getMobileNo().isEmpty()) {
				String phoneNumber = employeeModel.getMobileNo();
				if (!phoneNumber.startsWith("+91")) {
					phoneNumber = "+91" + phoneNumber;
				}
				employeeDetails.setMobileNo(phoneNumber);
			}
			if (employeeModel.getDepartment() != null && !employeeModel.getDepartment().isEmpty()) {
				employeeDetails.setDepartment(employeeModel.getDepartment());
			}
			if (employeeModel.getDesignation() != null && !employeeModel.getDesignation().isEmpty()) {
				employeeDetails.setDesignation(employeeModel.getDesignation());
			}

			if (employeeModel.getRoles() != null && !employeeModel.getRoles().isEmpty()) {
				employeeDetails.setRoles(employeeModel.getRoles());
			}
			if (employeeModel.getCity() != null && !employeeModel.getCity().isEmpty()) {
				employeeDetails.setCity(employeeModel.getCity());
			}
			if (employeeModel.getPincode() != null && !employeeModel.getPincode().isEmpty()) {
				employeeDetails.setPincode(employeeModel.getPincode());
			}
			if (employeeModel.getEmployeeStatus() != null && !employeeModel.getEmployeeStatus().isEmpty()) {
				employeeDetails.setEmployeeStatus(employeeModel.getEmployeeStatus());
			}

			if (employeeModel.getManagerEmpId() != null) {
				employeeDetails.setManagerEmpId(employeeModel.getManagerEmpId());
			}
			if (!StringsUtil.isNullOrEmpty(employeeModel.getManagerEmailId())) {
				employeeDetails.setManagerEmailId(employeeModel.getManagerEmailId());
			}
			employeeRepository.save(employeeDetails);
			// employeeDetails = modelMapper.map(employeeDetails, EmployeeUser.class);
			return employeeDetails;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while updating the employee:", e);
			throw e;
		}
	}

}
