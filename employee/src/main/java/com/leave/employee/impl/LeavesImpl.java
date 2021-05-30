package com.leave.employee.impl;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leave.employee.config.Constants;
import com.leave.employee.domain.EmployeeAttendance;
import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.domain.HolidayCollection;
import com.leave.employee.domain.LeaveStatistics;
import com.leave.employee.domain.LeaveTypeBalance;
import com.leave.employee.domain.LeavesDetails;
import com.leave.employee.exception.LeaveIsRejectedException;
import com.leave.employee.exception.NoLeavesAvailableException;
import com.leave.employee.model.AttendanceCsvResponse;
import com.leave.employee.repository.AttendanceRepository;
import com.leave.employee.repository.EmployeeAttendanceCustomRepository;
import com.leave.employee.repository.EmployeeRepository;
import com.leave.employee.repository.HolidayRepository;
import com.leave.employee.repository.LeaveRepository;
import com.leave.employee.repository.LeaveStatisticsRepository;
import com.leave.employee.repository.RoleRepository;
import com.leave.employee.service.LeaveService;
import com.opencsv.CSVWriter;

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
	private EmployeeAttendanceCustomRepository employeeAttendanceCustomRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Value("${filepath}")
	private String filepath;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public LeavesDetails applyLeave(LeavesDetails leavesDetails) {
		try {
			EmployeeUser empObj = employeeRepository.findByEmployeeId(leavesDetails.getEmployeeId());
			EmployeeUser managerObj = employeeRepository.findByEmployeeId(leavesDetails.getEmployeeId());
			LeaveStatistics leaveStatistics = leaveStatisticsRepository.findByEmployeeId(empObj.getEmployeeId());

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
			List<LocalDate> differences = new ArrayList<>(finalHolidays);
			differences.removeAll(finalHol);

			System.out.println(differences);
			double appliedLeaves = differences.size();
			if (leavesDetails.getLeaveDay().equalsIgnoreCase(Constants.HALF)) {
				appliedLeaves = appliedLeaves - 0.5;
			}
			emailService.sendEmail(managerObj, empObj, appliedLeaves);
			LeavesDetails leaveObj = new LeavesDetails();
			leaveObj.setEmployeeId(leavesDetails.getEmployeeId());
			leaveObj.setRequestDate(LocalDate.now());
			leaveObj.setLeaveStartDate(leavesDetails.getLeaveStartDate());
			leaveObj.setLeaveEndDate(leavesDetails.getLeaveEndDate());
			leaveObj.setLeaveStatus(Constants.PENDING);
			leaveObj.setLeaveType(leavesDetails.getLeaveType());
			leaveObj.setLeaveDay(leavesDetails.getLeaveDay());
			leaveObj.setLeaveReason(leavesDetails.getLeaveReason());
			leaveObj.setManagerEmpId(leavesDetails.getManagerEmpId());
			leaveObj.setDepartment(empObj.getDepartment());
			leaveObj = leaveRepository.save(leaveObj);
			leaveObj = modelMapper.map(leaveObj, LeavesDetails.class);
			return leaveObj;
		} catch (Exception e) {
			logger.error("Error occurred while applying the leave:", e);
			throw e;
		}
	}

	@Override
	public LeavesDetails updateLeave(LeavesDetails leavesDetails)
			throws NoLeavesAvailableException, LeaveIsRejectedException, ParseException {
		EmployeeUser empObj = employeeRepository.findByEmployeeId(leavesDetails.getEmployeeId());
		EmployeeUser managerObj = employeeRepository.findByEmployeeId(leavesDetails.getEmployeeId());
		LeaveStatistics leaveStatistics = leaveStatisticsRepository.findByEmployeeId(empObj.getEmployeeId());
		LeavesDetails leaveObj = leaveRepository.findByManagerEmpIdAndLeaveStatus(leavesDetails.getManagerEmpId(),
				Constants.PENDING);

		long numOfDays = ChronoUnit.DAYS.between(leaveObj.getLeaveStartDate(), leaveObj.getLeaveEndDate()) + 1;
		List<LocalDate> listOfDates = LongStream.range(0, numOfDays).mapToObj(leaveObj.getLeaveStartDate()::plusDays)
				.collect(Collectors.toList());

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
		List<LocalDate> differences = new ArrayList<>(finalHolidays);
		differences.removeAll(finalHol);

		System.out.println(differences);
		double appliedLeaves = differences.size();
		if (leavesDetails.getLeaveDay().equalsIgnoreCase(Constants.HALF)) {
			appliedLeaves = appliedLeaves - 0.5;
		}

		for (LeaveTypeBalance leaveBalance : leaveStatistics.getLeaveTypeBalance()) {
			if (leavesDetails.getLeaveType().equals(leaveBalance.getLeaveType())) {
				double noOfLeavesAvailable = leaveBalance.getNoOfDays();
				if (noOfLeavesAvailable >= appliedLeaves) {
					if (leavesDetails.getLeaveStatus().equalsIgnoreCase(Constants.APPROVED)) {
						leaveObj.setApprovalDate(LocalDate.now());
						leaveObj.setLeaveStatus(leavesDetails.getLeaveStatus());
						double finalLeaveBalance = leaveBalance.getNoOfDays() - appliedLeaves;
						for (int i = 0; i < leaveStatistics.getLeaveTypeBalance().size(); i++) {
							if (leaveStatistics.getLeaveTypeBalance().get(i).getLeaveType()
									.equalsIgnoreCase(leaveObj.getLeaveType())) {
								leaveStatistics.getLeaveTypeBalance().get(i).setNoOfDays(finalLeaveBalance);
							}
						}
						leaveStatisticsRepository.save(leaveStatistics);
						leaveObj = leaveRepository.save(leaveObj);
						leaveObj = modelMapper.map(leaveObj, LeavesDetails.class);
						for (LocalDate leaveApprovedDates : differences) {
							String date = leaveApprovedDates.toString();
							SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_PATTERN);
							Date date1 = formatter.parse(date);
							EmployeeAttendance empAttObj = new EmployeeAttendance();
							empAttObj.setEmployeeId(empObj.getEmployeeId());
							empAttObj.setNormalDate(date1);
							empAttObj.setTimeIn("");
							empAttObj.setTimeOut("");
							empAttObj.setWorking(8);
							empAttObj.setLocation("null");
							empAttObj = attendanceRepository.save(empAttObj);
						}
					} else {
						throw new LeaveIsRejectedException("Leave is Rejected");
					}
				} else {
					if (leavesDetails.getLeaveStatus().equalsIgnoreCase(Constants.APPROVED)) {
						leaveObj.setApprovalDate(LocalDate.now());
						leaveObj.setLeaveStatus(leavesDetails.getLeaveStatus());
						double count = 0;
						count = appliedLeaves;
						for (int i = 0; i < leaveStatistics.getLeaveTypeBalance().size(); i++) {
							if (leaveStatistics.getLeaveTypeBalance().get(i).getLeaveType()
									.equalsIgnoreCase(Constants.LOSS_OF_PAY)) {
								double lossOfPayCount = leaveStatistics.getLeaveTypeBalance().get(i).getNoOfDays();
								leaveStatistics.getLeaveTypeBalance().get(i)
										.setNoOfDays(appliedLeaves + lossOfPayCount);
							}
						}
						leaveStatisticsRepository.save(leaveStatistics);
						leaveObj = leaveRepository.save(leaveObj);
						leaveObj = modelMapper.map(leaveObj, LeavesDetails.class);
					} else {
						throw new LeaveIsRejectedException("Leave is Rejected");
					}
				}
			}
		}
		return leaveObj;
	}

//	@Scheduled(cron="0 0 1 */6 *")
	// @Scheduled(cron="*/15 * * * * *")
	public void performTaskUsingCron() {
		System.out.println("Hello");
		List<LeaveStatistics> leaveStatistics = leaveStatisticsRepository.findAll();
		for (LeaveStatistics leaveBalance : leaveStatistics) {
			for (LeaveTypeBalance leaveBalance1 : leaveBalance.getLeaveTypeBalance()) {
				if (leaveBalance1.getLeaveType().equalsIgnoreCase(Constants.EARNED_LEAVE)) {
					double noOfLeavesAvailable = leaveBalance1.getNoOfDays();
					noOfLeavesAvailable = noOfLeavesAvailable + 12;
					leaveBalance.getLeaveTypeBalance().get(0).setNoOfDays(noOfLeavesAvailable);
					leaveStatisticsRepository.save(leaveBalance);
				} else {
					if (leaveBalance1.getLeaveType().equalsIgnoreCase(Constants.SICK_LEAVE)) {
						double noOfLeavesAvailable = leaveBalance1.getNoOfDays();
						noOfLeavesAvailable = noOfLeavesAvailable + 3;
						leaveBalance.getLeaveTypeBalance().get(1).setNoOfDays(noOfLeavesAvailable);
						leaveStatisticsRepository.save(leaveBalance);
					}
				}
			}
		}
	}

	@Override
	public Boolean getAttendanceReport(Date startDate, Date endDate, Integer employeeId) {
		String pattern = Constants.DATE_PATTERN;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fileDate = simpleDateFormat.format(new Date());
		String fileName = filepath + fileDate + ".csv";
		File file = new File(fileName);
		try {
			List<EmployeeAttendance> employeeAttendance = employeeAttendanceCustomRepository
					.findEmpAttendanceRecords(startDate, endDate, employeeId);
			float sum = 0;
			for (EmployeeAttendance workingHours : employeeAttendance) {
				float working = workingHours.getWorking();
				sum = sum + working;
			}
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);
			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);
			String[] header = { Constants.SNO, Constants.EMPLOYEE_ID, Constants.START_DATE, Constants.END_DATE,
					Constants.WORKING_HOURS };
			writer.writeNext(header);
			AttendanceCsvResponse empAttendance = new AttendanceCsvResponse();
			String sno = "1";
			String employeeId1 = String.valueOf(employeeId);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String startDate1 = dateFormat.format(startDate);
			String endDate1 = dateFormat.format(endDate);
			String working1 = String.valueOf(sum);

			empAttendance.setSno(sno);
			empAttendance.setEmployeeId(employeeId1);
			empAttendance.setStartDate(startDate1);
			empAttendance.setEndDate(endDate1);
			empAttendance.setWorking(working1);
			// add data to csv
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { empAttendance.getSno(), empAttendance.getEmployeeId(), empAttendance.getStartDate(),
					empAttendance.getEndDate(), empAttendance.getWorking() });
			writer.writeAll(data);
			writer.close();
			// closing writer connection
			logger.info("--------Exit from generateCSV()---------");
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

}
