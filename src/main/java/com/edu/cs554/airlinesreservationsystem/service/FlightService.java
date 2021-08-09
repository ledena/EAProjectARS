package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.FlightSearchDto;
import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Flight;

import java.util.List;

public interface FlightService {
     List<Flight> findAll();
     List<Airline> getAirlinesFlyingOut(String airportCode);
     List<Flight> findFlights(FlightSearchDto flightSearchDto);
}
