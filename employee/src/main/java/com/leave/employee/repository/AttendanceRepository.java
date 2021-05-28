package com.leave.employee.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.leave.employee.domain.EmployeeAttendance;

@Repository
public interface AttendanceRepository extends MongoRepository<EmployeeAttendance, String>{
	
}
