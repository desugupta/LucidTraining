package com.leave.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leave.employee.domain.EmployeeAttendance;
import com.leave.employee.domain.ResponseObject;
import com.leave.employee.domain.Role;
import com.leave.employee.service.AttendanceService;
import com.leave.employee.util.ResponseUtil;

@RequestMapping("/attendance")
@RestController
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;
	
	private final Logger logger = LoggerFactory.getLogger(RolesRest.class);

	/**
	 * @author rajasekhar.d
	 * @description To save the attendance record in database
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveAttendance(@RequestBody EmployeeAttendance attendance) {
		try {
			logger.info("+++++ Entry into saveAttendance() method in Controller +++++");
			EmployeeAttendance attedance = attendanceService.saveAttendance(attendance);
			logger.info("+++++ Exit from saveAttendance() method in Controller +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(attedance), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in saving the employee attendance:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
