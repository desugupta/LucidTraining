package com.leave.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leave.employee.domain.HolidaysList;

public interface HolidayRepository extends MongoRepository<HolidaysList, String>{
	
	

}
