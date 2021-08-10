package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Airport;

import java.util.List;

public interface AirlineService {
    List<Airline> findAll();
    Airline save(Airline airline);
    Airline update(long id, Airline airline);
    Airline delete(long id);
}
