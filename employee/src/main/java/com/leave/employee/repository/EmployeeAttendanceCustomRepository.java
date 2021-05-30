package com.leave.employee.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.leave.employee.domain.EmployeeAttendance;

@Repository
public class EmployeeAttendanceCustomRepository {
		
	@Autowired
	private MongoOperations mongoOperations;	
	
	public List<EmployeeAttendance> findEmpAttendanceRecords(Date startDate,Date endDate, Integer employeeId) {
		Query query = new Query(Criteria.where("normalDate").gte(startDate).lte(endDate).and("employeeId").is(employeeId));
		List<EmployeeAttendance> empAttendanceRecords = mongoOperations.find(query, EmployeeAttendance.class);
		return empAttendanceRecords;
	}
	
}

