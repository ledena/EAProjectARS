package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
//
//@Repository
//@Transactional
//public interface AirlineRepository extends BaseRepository<Airline, Integer>{
//}

@Repository
@Transactional
public interface AirlineRepository  extends JpaRepository<Airline, Long> {

}