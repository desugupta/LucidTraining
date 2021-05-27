package com.leave.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.leave.employee.domain.HolidayCollection;

@Repository
public interface HolidayRepository extends MongoRepository<HolidayCollection, String>{
	
	

}
