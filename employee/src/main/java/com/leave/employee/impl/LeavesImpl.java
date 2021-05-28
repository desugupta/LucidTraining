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
import org.springframework.data.mongodb.core.MongoTemplate;
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

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private RoleRepository roleRepository;

	public final static String APPLICATION_NO = "Application No";
	public final static String APPLICANTS_NAME = "Applicant's Name";
	public final static String INSURED_NAME = "Insured Name";
	public final static String RELATIONSHIP = "Relationship of Insured with Applicant";
	public final static String DATEOFBIRTH = "DateOfBirth";
	public final static String SEX = "Sex";
	public final static String OOCUPATION_APPLICANT = "Occupation(Applicant)";
	public final static String MAILING_ADDRESS_FINAL = "Mailing Address Final";
	public final static String CITY_TOWN_VILLAGE = "City/Town/Village";
	public final static String STATE = "State";
	public final static String PINCODE = "PinCode";
	public final static String MOBILE_NUMBER = "Mobile Number";
	public final static String AADHAR_NO = "Aadhar No";
	public final static String APPLICATION_NO_LAN_NO = "Application No/Lan No";
	public final static String POLICY_START_DATE_ENROLLMENT_DATE = "Policy Start Date/Enrollment Date";
	public final static String PAN_DETAILS = "Pan Details";
	public final static String PREMIUM_RECEIVED_FROM_CUSTOMER = "Premium Received From Customer";
	public final static String CP_COI_NO_ALTERNATE_POLICY_NO = "COI No";
	public final static String EMAIL_ADDRESS = "Email address";
	public final static String NOMINEE_NAME1 = "Nominee Name1";
	public final static String NOMINEE_RELATIONSHIP_WITH_INSURED = "Nominee Relationship with Insured";
	public final static String PED_DETAILS = "PED Details";
	public final static String GSTIN_UIN_NO = "GSTIN/UIN No";
	public final static String RELATIONSHIP_MNGR_NAME_BANK = "RELATIONSHIP_MNGR_NAME(bank)";
	public final static String SP_CODE = "SP Code";
	public final static String DATE_PATTERN = "dd-MM-YYYY";
	public final static String STATUS = "PAYMENT_COMPLETED";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private static final String SNO = "S.no";
	private static final String EMPLOYEE_ID = "EmployeeId";
	private static final String START_DATE = "StartDate";
	private static final String END_DATE = "EndDate";
	private static final String WORKING_HOURS = "WorkingHours";

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

						for (LocalDate leaveApprovedDates : finalHolidays) {
							String unDATE = leaveApprovedDates.toString();

							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = formatter.parse(unDATE);
							EmployeeAttendance empAttObj = new EmployeeAttendance();

							empAttObj.setEmployeeId(empObj.getEmployeeId());
							empAttObj.setNormalDate(date1);
							empAttObj.setTimeIn("");
							empAttObj.setTimeOut("");
							empAttObj.setWorking(8);
							empAttObj.setLocation("null");
							empAttObj = attendanceRepository.save(empAttObj);

						}
						// emailService.sendEmail(empObj, appliedLeaves);
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
						// emailService.sendEmaiTemplate(empObj, appliedLeaves, managerObj, leaveObj);

						// throw new NoLeavesAvailableException("Leaves applied type are not
						// available");
					} else {
						throw new LeaveIsRejectedException("Leave is Rejected");
					}
				}
			}
		}
		return leaveObj;
	}

	// @Scheduled(cron = "*/30 * * * * *")
	public void performTaskUsingCron() {
		System.out.println("Hello");
		List<LeaveStatistics> leaveStatistics = leaveStatisticsRepository.findAll();
		for (LeaveStatistics leaveBalance : leaveStatistics) {
			for (LeaveTypeBalance leaveBalance1 : leaveBalance.getLeaveTypeBalance()) {
				if (leaveBalance1.getLeaveType().equalsIgnoreCase("earnedLeave")) {
					double noOfLeavesAvailable = leaveBalance1.getNoOfDays();
					noOfLeavesAvailable = noOfLeavesAvailable + 12;
					leaveBalance.getLeaveTypeBalance().get(0).setNoOfDays(noOfLeavesAvailable);
					leaveStatisticsRepository.save(leaveBalance);
				} else {
					if (leaveBalance1.getLeaveType().equalsIgnoreCase("sickLeave")) {

						double noOfLeavesAvailable = leaveBalance1.getNoOfDays();
						noOfLeavesAvailable = noOfLeavesAvailable + 3;
						leaveBalance.getLeaveTypeBalance().get(1).setNoOfDays(noOfLeavesAvailable);
						leaveStatisticsRepository.save(leaveBalance);
					}
				}
			}
		}
	}

	// @Scheduled(cron="*/5 * * * * *")
	public void performTaskUsingCron1() {
		System.out.println("Regular task performed using Cron at " + dateFormat.format(new Date()));
		logger.info("hlki");
	}

	@Override
	public Boolean getAttendanceReport(Date startDate, Date endDate, Integer employeeId) {
		String pattern = DATE_PATTERN;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fileDate = simpleDateFormat.format(new Date());
		String fileName = "/temp/AttendanceInformationReport_" + fileDate + ".csv";
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
			String[] header = { SNO, EMPLOYEE_ID, START_DATE, END_DATE, WORKING_HOURS };

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
			// sendEmailForICICI(fileName);
			// closing writer connection
			logger.info("--------Exit from generateCSV()---------");
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

}
