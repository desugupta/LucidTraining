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
import com.leave.employee.domain.LeavesDetails;
import com.leave.employee.domain.ResponseObject;
import com.leave.employee.service.AttendanceService;
import com.leave.employee.service.LeaveService;
import com.leave.employee.util.ResponseUtil;


@RequestMapping("/leave")
@RestController
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	private final Logger logger = LoggerFactory.getLogger(LeaveController.class);

	/**
	 * @author rajasekhar.d
	 * @description To apply for the leave
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> applyLeave(@RequestBody EmployeeAttendance attendance) {
		try {
			logger.info("+++++ Entry into applyLeave() method in Controller +++++");
			LeavesDetails leave = leaveService.applyLeave(attendance);
			logger.info("+++++ Exit from applyLeave() method in Controller +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(leave), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in saving the employee attendance:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}