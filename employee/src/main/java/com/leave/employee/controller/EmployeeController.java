package com.leave.employee.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.domain.ResponseObject;
import com.leave.employee.domain.Role;
import com.leave.employee.service.EmployeeService;
import com.leave.employee.util.ResponseUtil;

@RestController 
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	/**
	 * @author rajasekhar.d
	 * @description to save the employee
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeUser employeeUser) {
		try {
			logger.info("+++++ Entry into saveEmployee() method in Rest +++++");
			EmployeeUser employee = employeeService.saveEmployee(employeeUser);
			logger.info("+++++ Exit from saveEmployee() method in Rest +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(employee), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in saving the employee:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * @author rajasekhar.d
	 * @description to update the employee
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeUser employeeUser) {
		try {
			logger.info("+++++ Entry into updateRole method in Rest +++++");
			EmployeeUser employeeObj=employeeService.updateEmployee(employeeUser);
			logger.info("+++++ Exit from updateRole method in Rest +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(employeeObj), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in updating the role:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author rajasekhar.d
	 * @description to get the employee by employeeId
	 */
	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable("employeeId") int employeeId) {
		try {
			logger.info("+++++ Entry into getEmployee() method in Controller +++++");
			EmployeeUser employee = employeeService.getEmployee(employeeId);
			logger.info("+++++ Exit from getEmployee() method in Controller +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(employee), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting the employee by employeeId:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * @author rajasekhar.d
	 * @description to get all employees details available in the employee collection
	 */
	@RequestMapping(value="/getAll",method = RequestMethod.GET)
	public ResponseEntity<?> getAllEmployee() {
		try {
			logger.info("+++++ Entry into getAllEmployee() method in Controller +++++");
			List<EmployeeUser> employee = employeeService.getAllEmployees();
			logger.info("+++++ Exit from getAllEmployee() method in Controller +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(employee), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting all the employees list:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
}
