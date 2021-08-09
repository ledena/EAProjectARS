package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.dto.FlightPassengerResponseDto;
import com.edu.cs554.airlinesreservationsystem.dto.FlightSearchDto;
import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Flight;
import com.edu.cs554.airlinesreservationsystem.service.FlightService;
import com.edu.cs554.airlinesreservationsystem.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    public ModelMapper mapper;

    @Autowired
    public ListMapper listMapper;


    @GetMapping("")
    public List<Flight> findAll(){
        return flightService.findAll();
    }

    @GetMapping("out/airlines")
    public List<Airline> getAirlinesFlyingOutFromAirport(@RequestParam String airportCode){
        System.out.println("airportCode"+airportCode);
        return flightService.getAirlinesFlyingOut(airportCode);
    }
    @PostMapping("search")
    public List<FlightPassengerResponseDto> getFlights(@RequestBody FlightSearchDto flightSearchDto){
        List<Flight> flights = flightService.findFlights(flightSearchDto);
        return listMapper.mapList(flights, FlightPassengerResponseDto.class);
    }
}
