package com.leave.employee_repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leave.employee_model.EmployeeModel;

public interface EmployeeRepository extends MongoRepository<EmployeeModel, String> {

	EmployeeModel findById(int id);

	void deleteById(int id); 

}
