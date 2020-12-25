package com.cohad.mes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cohad.mes.entity.Receiver;

@Repository
public interface MesRepository extends JpaRepository<Receiver, Long>{

}
