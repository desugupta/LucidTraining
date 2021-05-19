package com.leave.employee.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.impl.JwtUtil;
import com.leave.employee.impl.MyUserDetailsService;
import com.leave.employee.impl.SequenceGeneratorService;
import com.leave.employee.model.AuthenticationRequest;
import com.leave.employee.model.AuthenticationResponse;
import com.leave.employee.repository.UserRepository;

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
	
	public static final String SEQUENCE_NAME="user_sequence";
	
	

	@PostMapping("/subscription")
	public ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {
		String userName = authenticationRequest.getUserName();
		String password = authenticationRequest.getPassword();
		EmployeeUser userModel = new EmployeeUser();
		userModel.setUserName(userName);
		userModel.setPassword(password);
		userModel.setEmployeeId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME)); 
		userModel=modelMapper.map(userModel, EmployeeUser.class);
		try { 
			userRepository.save(userModel);
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during client Subscription" + userName));
		}
		return ResponseEntity.ok(new AuthenticationResponse("Sucessful Subscription for client" + userName));
	}

	@PostMapping("/authentication")
	public ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
		String userName = authenticationRequest.getUserName();
		String password = authenticationRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during client Authentication" + userName));
		}
		
		final UserDetails loadedUser = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		final String generatedToken = jwtUtil.generateToken(loadedUser);

		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
	
		//return ResponseEntity.ok(new AuthenticationResponse("Sucessful Authentication for client" + userName));
	}
	
	@GetMapping("/dashboard")
	//@PreAuthorize("hasRole(admin)
	private String testingToken() {
		return "Welcome to the DashBoard  "+ SecurityContextHolder.getContext().getAuthentication().getName();
	}


}
