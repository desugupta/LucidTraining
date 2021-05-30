package com.leave.employee.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
		
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeUser foundedUser = userRepository.findByUserName(username);
		if (foundedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
			}
		String name = foundedUser.getUserName();
		String password = foundedUser.getPassword();
		return new User(name, password, getAuthority(foundedUser));
	}
		
	private Set getAuthority(EmployeeUser user) {
        Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

}