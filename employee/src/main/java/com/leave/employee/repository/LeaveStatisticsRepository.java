package com.leave.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.leave.employee.domain.LeaveStatistics;
import com.leave.employee.domain.LeaveTypeBalance;

@Repository
public interface LeaveStatisticsRepository extends MongoRepository<LeaveStatistics, String>{

	LeaveStatistics findByEmployeeId(Integer employeeId);

	//LeaveStatistics save(LeaveTypeBalance leaveBalance);
	
	//LeaveStatistics save(LeaveStatistics leaveStatistics);


}
