package com.leave.employee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.leave.employee.domain.EmployeeUser;

@Service	
public class EmailService {
	
	@Value("${app.emailFrom}")
	private String emailFrom;
	
	@Autowired
	JavaMailSender javaMailSender;

	/**
	 * @author rajasekhar.d
	 * @description To send the leave notification mail to the manager whenever employee requests for the leave
	 */
	public void sendEmail(EmployeeUser managerObj,EmployeeUser empObj, double appliedLeaves) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
	   // message.setTo(managerObj.getEmailId());
	    message.setTo(empObj.getManagerEmailId());
		message.setSubject("Employee Leave Notification");
		message.setText("Please approve or reject the leave of "+empObj.getUserName()+" for "+appliedLeaves+" days" );
		javaMailSender.send(message);
	}
	
}