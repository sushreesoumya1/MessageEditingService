package com.cohad.mes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cohad.mes.entity.Receiver;
import com.cohad.mes.service.MesService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class MesController {
	
	@Autowired
	MesService mesService;
	
	@PostMapping("/setReceiver")
		public String setReceiver(){
			mesService.insertReceiver();
			return "Insrted Data";
		}

	@GetMapping("/getReceivers")
	public List<Receiver> getAllReceivers(){
		return mesService.retrieveReceivers();
	}
	
	@GetMapping("/getReceiver/{id}")
	public Optional<Receiver> getReceiverById(@PathVariable long id){
		log.info("id: "+id);
		return mesService.retrieveReceiverById(id);
	}
	
	@PostMapping("/sendMsg")
	public String sendMessage(@RequestBody String msg){
		List<Receiver> receivers = new ArrayList<Receiver>();
		mesService.insertReceiver();
		receivers= mesService.retrieveReceivers();
		mesService.sendHardCodedMsg(receivers,msg);
		
		return "Message Sent";
		
	}
}
