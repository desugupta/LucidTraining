package com.leave.employee.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.leave.employee.domain.HolidayCollection;
import com.leave.employee.repository.HolidayRepository;
import com.leave.employee.service.HolidayService;

@Service
public class HolidaysListImpl implements HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @author rajasekhar.d
	 * @description To save the holiday details in the database
	 * @return HolidayCollection
	 */ 
	@Override
	public HolidayCollection saveHolidayInformation(HolidayCollection holidaysList) {
		try {
			HolidayCollection holidayObj = new HolidayCollection();
			holidayObj.setHolidayYear(holidaysList.getHolidayYear());
			holidayObj.setHolidayId(holidaysList.getHolidayId());
		//	holidayObj.setHolidayDate(holidaysList.getHolidayDate());
			holidayObj.setHoliday(holidaysList.getHoliday());

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
