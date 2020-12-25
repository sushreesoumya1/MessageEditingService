package com.cohad.mes.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cohad.mes.entity.Receiver;
import com.cohad.mes.repository.MesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MesService {
	
	@Autowired
	MesRepository mesRepo;
	@Autowired
	SendMail sendMail;
	@Autowired
	JavaMailSender mailSender;
	static int i=1;
	
	String finalMsg;
	
	public List<Receiver> retrieveReceivers(){
		List<Receiver> receivers = new ArrayList<Receiver>();
		log.info("Retrieving receivers");
		receivers=mesRepo.findAll();
		receivers.stream().forEach(r->System.out.println(r.name+" "+r.email+" "+r.mob));
		return receivers;
	}
	public Optional<Receiver> retrieveReceiverById(long id){
		log.info("Retrieving receiver by ID");
		return mesRepo.findById(id);
		
	}
	
	public void insertReceiver(){
		try{
		List<Receiver> saveReceivers = new ArrayList<Receiver>();
		for(int j=0; j<5;j++)
			saveReceivers.add(new Receiver(j,"F"+j,new BigDecimal("123456789"),"xxxx@gmail.com"));
		
		mesRepo.saveAll(saveReceivers);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void sendHardCodedMsg(List<Receiver> receivers, String msg){
		try{
			Instant ins1 =Instant.now();
			log.info("message: "+finalMsg);
			finalMsg= msg;
			log.info("MESSGAE: "+msg);
			sendMail.sendMailToOneReceiver(receivers.get(0).email,msg);
			
			receivers.parallelStream()
			.forEach(r-> sendMail.sendMailToOneReceiver(
					r.email, editFinalMsg(r.name,finalMsg))
					);
			log.info("Editing Done...");
			Instant ins2 =Instant.now();
			
			log.info("Time Taken: "+Duration.between(ins2, ins1));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String editFinalMsg(String name, String msg){
		String editedMsg="";
		try{
			log.info("original msg..."+msg);
			editedMsg= "Hi "+name+","+"\n"+msg;
			log.info("Edited msg: "+editedMsg);
			return editedMsg;
		}catch(Exception e){
			log.error("ERROR in editing msg");
			e.printStackTrace();
		}
		return editedMsg;
	}

}
