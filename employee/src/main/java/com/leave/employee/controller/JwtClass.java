package com.leave.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jwt")
@RestController
public class JwtClass {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	
	/*
	 * server.port=9442
	 * 
	 * spring.data.mongodb.host=localhost spring.data.mongodb.port=27017
	 * spring.data.mongodb.database=attendanceLeaveManagement
	 */

	

}
