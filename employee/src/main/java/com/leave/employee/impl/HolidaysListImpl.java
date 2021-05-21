package com.leave.employee.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leave.employee.domain.HolidaysList;
import com.leave.employee.repository.HolidayRepository;
import com.leave.employee.service.HolidayService;

@Service
public class HolidaysListImpl implements HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public HolidaysList saveHolidayDate(HolidaysList holidaysList) {
		try {
			HolidaysList holidayObj = new HolidaysList();
			holidayObj.setHolidayId(holidaysList.getHolidayId());
			holidayObj.setHolidayDate(holidaysList.getHolidayDate());
			holidayObj.setReason(holidaysList.getReason());
			holidayObj = holidayRepository.save(holidayObj);
			return holidayObj;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving the holiday details:", e);
			throw e;
		}
	}

}
