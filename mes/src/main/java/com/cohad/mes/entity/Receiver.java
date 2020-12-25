package com.cohad.mes.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
public class Receiver implements Serializable{
		
		@Id
		@GeneratedValue
		public long id;
		public String name;
		public BigDecimal mob;
		public String email;
		
		public Receiver(Integer id, String name, BigDecimal mob, String email) {
			super();
			this.id = id;
			this.name = name;
			this.mob = mob;
			this.email = email;
		}
		
		public Receiver(){
			
		}

}
