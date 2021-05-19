package com.leave.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.leave.employee.domain.EmployeeUser;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeUser, String> {

	public EmployeeUser findByEmployeeId(int employeeId);

}
