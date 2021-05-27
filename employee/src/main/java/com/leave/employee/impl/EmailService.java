package com.leave.employee.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.leave.employee.domain.EmployeeUser;
import com.leave.employee.domain.LeavesDetails;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;

	public void sendEmail(EmployeeUser empObj, double appliedLeaves, LeavesDetails leaveObj) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("rajasekhargupta30@gmail.com");
	    message.setTo("desurajasekhargupta437@gmail.com");
		message.setSubject("Employee Leave Notification");
		message.setText("Please approve or reject the leave of "+empObj.getUserName()+" for "+appliedLeaves+"days" );
		javaMailSender.send(message);
	//	return "Mail sent successfully";
	}
	
	
	public String sendEmailwithAttachment() {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			
			MimeMessageHelper messageHelper = 
					new MimeMessageHelper(message, true);
			
			messageHelper.setFrom("");
			messageHelper.setTo("");
			messageHelper.setSubject("Test Subject");
			messageHelper.setText("Test Body");
			
			File file = new File("Path To File");
			
			messageHelper.addAttachment(file.getName(), file);
			
			javaMailSender.send(message);
			
			return "Mail sent successfully";
			
		} catch (Exception e) {
			return "Mail sent failed";
		}
	}

	public void sendEmaiTemplate(EmployeeUser empObj, double appliedLeaves, EmployeeUser managerObj,
			LeavesDetails leaveObj) throws MessagingException {

		String from = "rajasekhargupta30@gmail.com";
		String to = "desurajasekhargupta437@gmail.com";
		 
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		 
		helper.setSubject("Employee Leave Notification");
		helper.setFrom(from);
		helper.setTo(to);
		 
		boolean html = true;
		helper.setText("<b>Hey guys</b>,<br><i>Welcome to my new home</i>", html);
		 
		javaMailSender.send(message);}
}