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

	@Override
	public EmployeeAttendance saveAttendance(EmployeeAttendance employeeAttendance) throws ParseException {
		try {
			EmployeeAttendance employeeAttendanceObj = new EmployeeAttendance();

			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			String timeIn = employeeAttendance.getTimeIn();
			String timeOut = employeeAttendance.getTimeOut();

			Date date1 = sdf.parse(employeeAttendance.getTimeIn());
			Date date2 = sdf.parse(employeeAttendance.getTimeOut());

			long time_difference = date2.getTime() - date1.getTime();
			long minutes_difference = (time_difference / (1000 * 60)) % 60;
			long hours_difference = (time_difference / (1000 * 60 * 60)) % 24;

			String workingHours = String.valueOf(hours_difference) + ":" + String.valueOf(minutes_difference);

			employeeAttendanceObj.setEmployeeId(employeeAttendance.getEmployeeId());
			employeeAttendanceObj.setNormalDate(employeeAttendance.getNormalDate());
			employeeAttendanceObj.setTimeIn(timeIn);
			employeeAttendanceObj.setTimeOut(timeOut);
			employeeAttendanceObj.setWorkingHours(workingHours);
			employeeAttendanceObj.setLocation(employeeAttendance.getLocation());
			employeeAttendanceObj = attendanceRepository.save(employeeAttendanceObj);
			employeeAttendanceObj = modelMapper.map(employeeAttendanceObj, EmployeeAttendance.class);
			return employeeAttendanceObj;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving the employee attendance for the given date:"
					+ employeeAttendance.getNormalDate(), e);
			throw e;
		}
	}

}
