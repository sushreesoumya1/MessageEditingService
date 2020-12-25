package com.cohad.mes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendMail {
	
	@Autowired
	JavaMailSender mailSender;
	
	public void sendMailToOneReceiver(String email, String msg){
		try{
			log.info("Sending Mail..."+msg);
			SimpleMailMessage mailMsg= new SimpleMailMessage();
			mailMsg.setTo(email);
			mailMsg.setSubject("Party Invitation (Test Mail)");
			mailMsg.setText(msg);
			mailSender.send(mailMsg);
			log.info("Mail sent");
		}catch(Exception e){
			log.error("ERROR in sending Mail");
			e.printStackTrace();
		}
	}
	/*public void sendMailToMultiReceivers(List<String> email, String msg){
		try{
			
			log.info("Sending Mail..."+msg);
			SimpleMailMessage mailMsg= new SimpleMailMessage();
			mailMsg.setTo(email);
			mailMsg.setSubject("Party Invitation (Test Mail)");
			mailMsg.setText(msg);
			mailSender.send(mailMsg);
			log.info("Mail sent");
		}catch(Exception e){
			log.error("ERROR in sending Mail");
			e.printStackTrace();
		}
	}
*/
}
