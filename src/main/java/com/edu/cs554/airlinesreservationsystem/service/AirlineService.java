package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.model.Airline;

import java.util.List;
//
//public interface AirlineService extends BaseReadWriteService<AirlineDto, Airline, Integer> {
//
//}

public interface AirlineService {
    List<Airline> findAll();
}
