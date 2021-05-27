package com.leave.employee.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.leave.employee.config.Constants;
import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.domain.HolidayCollection;
import com.leave.employee.domain.LeaveStatistics;
import com.leave.employee.domain.LeaveTypeBalance;
import com.leave.employee.domain.LeavesDetails;
import com.leave.employee.exception.LeaveIsRejectedException;
import com.leave.employee.exception.NoLeavesAvailableException;
import com.leave.employee.repository.AttendanceRepository;
import com.leave.employee.repository.EmployeeRepository;
import com.leave.employee.repository.HolidayRepository;
import com.leave.employee.repository.LeaveRepository;
import com.leave.employee.repository.LeaveStatisticsRepository;
import com.leave.employee.repository.RoleRepository;
import com.leave.employee.service.LeaveService;

@Service
public class LeavesImpl implements LeaveService {
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LeaveRepository leaveRepository;

	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private LeaveStatisticsRepository leaveStatisticsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private RoleRepository roleRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public LeavesDetails applyLeave(LeavesDetails leavesDetails) {
		try {
			EmployeeUser empObj = employeeRepository.findByEmployeeId(leavesDetails.getEmployeeId());

			LeaveStatistics leaveStatistics = leaveStatisticsRepository.findByEmployeeId(leavesDetails.getEmployeeId());
			long numOfDays = ChronoUnit.DAYS.between(leavesDetails.getLeaveStartDate(), leavesDetails.getLeaveEndDate())
					+ 1;

			List<LocalDate> listOfDates = LongStream.range(0, numOfDays)
					.mapToObj(leavesDetails.getLeaveStartDate()::plusDays).collect(Collectors.toList());
			List<LocalDate> finalHolidays = new ArrayList<LocalDate>();
			List<LocalDate> finalHol = new ArrayList<LocalDate>();

			List<HolidayCollection> holidaysDb = holidayRepository.findAll();

			for (HolidayCollection hol : holidaysDb) {
				finalHol.add(hol.getHoliday());

			}

			for (LocalDate listDates : listOfDates) {
				if (listDates.getDayOfWeek() != DayOfWeek.SATURDAY && listDates.getDayOfWeek() != DayOfWeek.SUNDAY) {
					finalHolidays.add(listDates);
				}
			}

			Iterator<LocalDate> itr = finalHolidays.iterator();
			while (itr.hasNext()) {
				LocalDate date = itr.next();

				Iterator<LocalDate> itr1 = finalHol.iterator();
				while (itr1.hasNext()) {

					LocalDate date1 = itr1.next();
					if (date.equals(date1)) {
						itr.remove();

					}
				}
			}
			System.out.println(finalHolidays);
			// if(leaveStatistics.getLeaveTypeBalance().getfinalHolidays.size())
			LeavesDetails leaveObj = new LeavesDetails();
			leaveObj.setEmployeeId(leavesDetails.getEmployeeId());
			leaveObj.setRequestDate(LocalDate.now());
			leaveObj.setLeaveStartDate(leavesDetails.getLeaveStartDate());
			leaveObj.setLeaveEndDate(leavesDetails.getLeaveEndDate());
			leaveObj.setLeaveStatus(Constants.PENDING);
			leaveObj.setLeaveType(leavesDetails.getLeaveType());
			leaveObj.setLeaveDay(leavesDetails.getLeaveDay());
			leaveObj.setLeaveReason(leavesDetails.getLeaveReason());
			leaveObj.setManagerEmpId(empObj.getManagerEmpId());
			leaveObj.setDepartment(empObj.getDepartment());
			leaveObj = leaveRepository.save(leaveObj);
			leaveObj = modelMapper.map(leaveObj, LeavesDetails.class);
			return leaveObj;
		} catch (Exception e) {
			logger.error("Error occurred while applying the leave:", e);
			throw e;
		}
	}

	public static void main(String args[]) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		System.out.println(dtf.format(now));
	}

	@Transactional
	@Override
	public LeavesDetails updateLeave(LeavesDetails leavesDetails)
			throws NoLeavesAvailableException, LeaveIsRejectedException {
		EmployeeUser empObj = employeeRepository.findByEmployeeId(leavesDetails.getEmployeeId());
		EmployeeUser managerObj=employeeRepository.findByEmployeeId(leavesDetails.getEmployeeId());
		LeaveStatistics leaveStatistics = leaveStatisticsRepository.findByEmployeeId(empObj.getEmployeeId());
		LeavesDetails leaveObj = leaveRepository.findByManagerEmpId(leavesDetails.getManagerEmpId());

		long numOfDays = ChronoUnit.DAYS.between(leaveObj.getLeaveStartDate(), leaveObj.getLeaveEndDate())
				+ 1;
		List<LocalDate> listOfDates = LongStream.range(0, numOfDays)
				.mapToObj(leavesDetails.getLeaveStartDate()::plusDays).collect(Collectors.toList());

		List<LocalDate> finalHolidays = new ArrayList<LocalDate>();
		List<LocalDate> finalHol = new ArrayList<LocalDate>();

		List<HolidayCollection> holidaysDb = holidayRepository.findAll();

		for (HolidayCollection hol : holidaysDb) {
			finalHol.add(hol.getHoliday());
		}

		for (LocalDate listDates : listOfDates) {
			if (listDates.getDayOfWeek() != DayOfWeek.SATURDAY && listDates.getDayOfWeek() != DayOfWeek.SUNDAY) {
				finalHolidays.add(listDates);
			}
		}

		Iterator<LocalDate> itr = finalHolidays.iterator();
		while (itr.hasNext()) {
			LocalDate date = itr.next();
			Iterator<LocalDate> itr1 = finalHol.iterator();
			while (itr1.hasNext()) {
				LocalDate date1 = itr1.next();
				if (date.equals(date1)) {
					itr.remove();
				}
			}
		}
		System.out.println(finalHolidays);
		double appliedLeaves = finalHolidays.size();
		if (leavesDetails.getLeaveDay().equalsIgnoreCase("Half")) {
			appliedLeaves = appliedLeaves - 0.5;
		}

		// double noOfLeavesAvailable =
		// leaveStatistics.getLeaveTypeBalance().get.getNoOfDays();

		for (LeaveTypeBalance leaveBalance : leaveStatistics.getLeaveTypeBalance()) {
			if (leavesDetails.getLeaveType().equals(leaveBalance.getLeaveType())) {
				double noOfLeavesAvailable = leaveBalance.getNoOfDays();
				if (noOfLeavesAvailable >= appliedLeaves) {
					if (leavesDetails.getLeaveStatus().equalsIgnoreCase(Constants.APPROVED)) {
						leaveObj.setApprovalDate(LocalDate.now());
						leaveObj.setLeaveStatus(leavesDetails.getLeaveStatus());
						// leaveObj.setRequestDate(LocalDate.now());
						double finalLeaveBalance = leaveBalance.getNoOfDays() - appliedLeaves;
						// leaveBalance.setNoOfDays(finalLeaveBalance);
						if (leavesDetails.getLeaveType().equalsIgnoreCase("earnedLeave")) {
							leaveStatistics.getLeaveTypeBalance().get(0).setNoOfDays(finalLeaveBalance);
						} else {
							leaveStatistics.getLeaveTypeBalance().get(1).setNoOfDays(finalLeaveBalance);
						}
						leaveStatisticsRepository.save(leaveStatistics);
						leaveObj = leaveRepository.save(leaveObj);
						leaveObj = modelMapper.map(leaveObj, LeavesDetails.class);
						emailService.sendEmail(empObj,appliedLeaves);
					} else {
						throw new LeaveIsRejectedException("Leave is Rejected");
					} 
				} else {
					if (leavesDetails.getLeaveStatus().equalsIgnoreCase(Constants.APPROVED)) {
						leaveObj.setApprovalDate(LocalDate.now());
						leaveObj.setLeaveStatus(leavesDetails.getLeaveStatus());
						double count = 0;
						count = appliedLeaves;
						leaveBalance.getLeaveType().equalsIgnoreCase(Constants.LOSS_OF_PAY);
						double lossOfPayCount = leaveStatistics.getLeaveTypeBalance().get(2).getNoOfDays();
						lossOfPayCount = count + lossOfPayCount;
						leaveStatistics.getLeaveTypeBalance().get(2).setNoOfDays(lossOfPayCount);
						leaveStatisticsRepository.save(leaveStatistics);
						leaveObj = leaveRepository.save(leaveObj);
						leaveObj = modelMapper.map(leaveObj, LeavesDetails.class);
						emailService.sendEmaiTemplate(empObj,appliedLeaves,managerObj,leaveObj);


						//throw new NoLeavesAvailableException("Leaves applied type are not available");
					} else {
						throw new LeaveIsRejectedException("Leave is Rejected");
					}
				}
			}
		}

		return leaveObj;
	}

}
