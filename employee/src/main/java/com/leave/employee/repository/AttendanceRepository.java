package com.leave.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.leave.employee.domain.EmployeeAttendance;

@Repository
public interface AttendanceRepository extends MongoRepository<EmployeeAttendance, String>{
		
}
