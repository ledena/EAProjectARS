package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.FlightSearchDto;
import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Flight;
import com.edu.cs554.airlinesreservationsystem.repository.AirlineRepository;
import com.edu.cs554.airlinesreservationsystem.repository.AirportRepository;
import com.edu.cs554.airlinesreservationsystem.repository.FlightRepository;
import com.edu.cs554.airlinesreservationsystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    public FlightRepository flightRepository;
    @Autowired
    public AirportRepository airportRepository;
    @Autowired
    public AirlineRepository airlineRepository;


    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Airline> getAirlinesFlyingOut(String airportCode) {
            return flightRepository.getAirlinesFlyingOut(airportCode);
    }

    @Override
    public List<Flight> findFlights(FlightSearchDto flightSearchDto) {

        System.out.println("Date"+flightSearchDto.getDate());
        return flightRepository.findFlights(flightSearchDto.getFrom(), flightSearchDto.getTo(), flightSearchDto.getDate());
    }


}
