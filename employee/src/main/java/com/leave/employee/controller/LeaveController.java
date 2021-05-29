package com.leave.employee.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leave.employee.domain.LeavesDetails;
import com.leave.employee.domain.ResponseObject;
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
	public ResponseEntity<?> applyLeave(@RequestBody LeavesDetails leavesDetails) {
		try {
			logger.info("+++++ Entry into applyLeave() method in Controller +++++");
			LeavesDetails leave = leaveService.applyLeave(leavesDetails);
			logger.info("+++++ Exit from applyLeave() method in Controller +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(leave), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in applying the leave:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author rajasekhar.d
	 * @description to update the leave request
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateLeave(@RequestBody LeavesDetails leavesDetails) {
		try {
			logger.info("+++++ Entry into updateLeave method in Rest +++++");
			LeavesDetails leave = leaveService.updateLeave(leavesDetails);
			logger.info("+++++ Exit from updateLeave method in Rest +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(leave), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in updating the leave:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> applyLeave(@RequestParam(value = "startDate", required = true)@DateTimeFormat(pattern = "yyyy-MM-dd")  Date startDate  ,
			@RequestParam(value = "endDate", required = true)@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "employeeId", required = true) Integer employeeId) {
		try {
			logger.info("+++++ Entry into applyLeave() method in Controller +++++");
			leaveService.getAttendanceReport(startDate, endDate,employeeId);
			logger.info("+++++ Exit from applyLeave() method in Controller +++++");
			return new ResponseEntity<ResponseObject<?>>(
					ResponseUtil.createSuccessResponse("File report downloaded successfully"), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in applying the leave:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}