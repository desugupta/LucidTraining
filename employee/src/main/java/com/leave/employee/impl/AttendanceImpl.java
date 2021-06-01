package com.leave.employee.impl;
		
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.employee.domain.EmployeeAttendance;
import com.leave.employee.repository.AttendanceRepository;
import com.leave.employee.service.AttendanceService;

@Service
public class AttendanceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private ModelMapper modelMapper;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @author rajasekhar.d
	 * @description To save the attendance record in database
	 * @RequestBody EmployeeAttendance
	 * @return EmployeeAttendance
	 */
	@Override
	public EmployeeAttendance saveAttendance(EmployeeAttendance employeeAttendance) throws ParseException {
		try {
			logger.info("+++++ Entry into saveAttendance() method in AttendanceImpl class +++++");
			EmployeeAttendance employeeAttendanceObj = new EmployeeAttendance();
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			String timeIn = employeeAttendance.getTimeIn();
			String timeOut = employeeAttendance.getTimeOut();

			Date date1 = sdf.parse(employeeAttendance.getTimeIn());
			Date date2 = sdf.parse(employeeAttendance.getTimeOut());

			long time_difference = date2.getTime() - date1.getTime();
			long minutes_difference = (time_difference / (1000 * 60)) % 60;
			long hours_difference = (time_difference / (1000 * 60 * 60)) % 24;
			
			float hours=(float)hours_difference;
			float min=(float)minutes_difference;
			float hr=min/60;
			float working=hours+hr;
			String workingHours=String.valueOf(working);
			workingHours=workingHours.substring(0,5);

			employeeAttendanceObj.setEmployeeId(employeeAttendance.getEmployeeId());
			employeeAttendanceObj.setNormalDate(employeeAttendance.getNormalDate());
			employeeAttendanceObj.setTimeIn(timeIn);
			employeeAttendanceObj.setTimeOut(timeOut);
			employeeAttendanceObj.setWorking(working);
			employeeAttendanceObj.setLocation(employeeAttendance.getLocation());
			employeeAttendanceObj = attendanceRepository.save(employeeAttendanceObj);
			employeeAttendanceObj = modelMapper.map(employeeAttendanceObj, EmployeeAttendance.class);
			logger.info("+++++ Exit from saveAttendance() method in AttendanceImpl class +++++");
			return employeeAttendanceObj;
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("Error occurred while saving the employee attendance for the given date:"
					+ employeeAttendance.getNormalDate(), exception);
			throw exception;
		}
	}
	
}
