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
import com.leave.employee.domain.HolidayCollection;
import com.leave.employee.domain.ResponseObject;
import com.leave.employee.service.HolidayService;
import com.leave.employee.util.ResponseUtil;

@RequestMapping("/holiday")
@RestController
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;
	
	private final Logger logger = LoggerFactory.getLogger(HolidayController.class);

	/**
	 * @author rajasekhar.d
	 * @description To save the holiday details in the database
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveHoliday(@RequestBody HolidayCollection holidaysList) {
		try {
			logger.info("+++++ Entry into saveHoliday() method in Controller +++++");
			HolidayCollection holiday = holidayService.saveHolidayInformation(holidaysList);
			logger.info("+++++ Exit from saveHoliday() method in Controller +++++");
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(holiday), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in saving the holiday information:", e.getMessage());
			return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createErrorResponse(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
