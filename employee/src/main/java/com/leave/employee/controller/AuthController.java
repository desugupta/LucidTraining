package com.leave.employee.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.domain.ResponseObject;
import com.leave.employee.impl.JwtUtil;
import com.leave.employee.impl.MyUserDetailsService;
import com.leave.employee.impl.SequenceGeneratorService;
import com.leave.employee.model.AuthenticationRequest;
import com.leave.employee.model.AuthenticationResponse;
import com.leave.employee.repository.UserRepository;
import com.leave.employee.util.ResponseUtil;

@RestController
public class AuthController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public static final String SEQUENCE_NAME = "user_sequence";

	/**
	 * @author rajasekhar.d
	 * @description To save the Employee user in the database
	 */
	@PostMapping("/subscription")
	public ResponseEntity<?> subscribeClient(@RequestBody EmployeeUser employeeUser) {
		EmployeeUser empObj = null;
		String phoneNumber = employeeUser.getMobileNo();
		if (!phoneNumber.startsWith("+91")) {
			phoneNumber = "+91" + phoneNumber;
		}
		employeeUser.setEmployeeId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
		employeeUser.setPassword(bcryptEncoder.encode(employeeUser.getPassword()));
		employeeUser.setMobileNo(phoneNumber);
		try {
			empObj = userRepository.save(employeeUser);
		} catch (Exception e) {
			return ResponseEntity
					.ok(new AuthenticationResponse("Error during client Subscription" + employeeUser.getUserName()));
		}
		return new ResponseEntity<ResponseObject<?>>(ResponseUtil.createSuccessResponse(empObj), HttpStatus.OK);

	}

	/**
	 * @author rajasekhar.d
	 * @description To authenticate the employee user and return the jwt token
	 */
	@PostMapping("/authentication")
	public ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
		String userName = authenticationRequest.getUserName();
		String password = authenticationRequest.getPassword();
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during client Authentication" + userName));
		}
		final UserDetails loadedUser = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String generatedToken = jwtUtil.createToken(authentication);
		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
	}

	@GetMapping("/dashboard")
	private String testingToken() {
		return "Welcome to the DashBoard  " + SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
