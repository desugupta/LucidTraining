package com.leave.employee.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
		return new User("desu", "desuu", new ArrayList<>());
	}
	
	/*
	 * server.port=9441
	 * 
	 * spring.data.mongodb.host=localhost spring.data.mongodb.port=27017
	 * spring.data.mongodb.database=candidateinformation
	 * ##spring.data.mongodb.database=healthinhands
	 */


}
