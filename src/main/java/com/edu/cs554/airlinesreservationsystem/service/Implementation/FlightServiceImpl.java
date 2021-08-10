package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.FlightSearchDto;
import com.edu.cs554.airlinesreservationsystem.dto.FlightRequest;
import com.edu.cs554.airlinesreservationsystem.exception.ResourceNotFoundException;
import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.model.Flight;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import com.edu.cs554.airlinesreservationsystem.repository.AirlineRepository;
import com.edu.cs554.airlinesreservationsystem.repository.AirportRepository;
import com.edu.cs554.airlinesreservationsystem.repository.FlightRepository;
import com.edu.cs554.airlinesreservationsystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Flight save(FlightRequest newFlight) {
        Airline newAirline = airlineRepository.findById(newFlight.getIdAirline())
                .orElseThrow(() -> new ResourceNotFoundException("Airline not exist with id :" + newFlight.getIdAirline()));

        Airport newDepartureAirport = airportRepository.findById(newFlight.getIdDepartureAirport())
                .orElseThrow(() -> new ResourceNotFoundException("Airport not exist with id :" + newFlight.getIdDepartureAirport()));

        Airport newArrivalAirport = airportRepository.findById(newFlight.getIdArrivalAirport())
                .orElseThrow(() -> new ResourceNotFoundException("Airport not exist with id :" + newFlight.getIdDepartureAirport()));

        Flight flight = new Flight(newFlight.getNumber(),
                                    newFlight.getCapacity(),
                                    newAirline,
                                    newFlight.getDepartureTime(),
                                    newDepartureAirport,
                                    newFlight.getArrivalTime(),
                                    newArrivalAirport);
        return flightRepository.save(flight);

    }


    @Override
    public Flight update(long id, FlightRequest newFlight) {

        Optional<Flight> optionalFlight = Optional.ofNullable(flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not exist with id :" + id)));

        Flight flight = optionalFlight.get();

        if(optionalFlight.isPresent()) {

            Airline newAirline = airlineRepository.findById(newFlight.getIdAirline())
                    .orElseThrow(() -> new ResourceNotFoundException("Airline not exist with id :" + newFlight.getIdAirline()));

            Airport newDepartureAirport = airportRepository.findById(newFlight.getIdDepartureAirport())
                    .orElseThrow(() -> new ResourceNotFoundException("Airport not exist with id :" + newFlight.getIdDepartureAirport()));

            Airport newArrivalAirport = airportRepository.findById(newFlight.getIdArrivalAirport())
                    .orElseThrow(() -> new ResourceNotFoundException("Airport not exist with id :" + newFlight.getIdDepartureAirport()));

            flight.setNumber(newFlight.getNumber());
            flight.setCapacity(newFlight.getCapacity());
            flight.setAirline(newAirline);
            flight.setDepartureTime(newFlight.getDepartureTime());
            flight.setDepartureAirport(newDepartureAirport);
            flight.setArrivalTime(newFlight.getArrivalTime());
            flight.setArrivalAirport(newArrivalAirport);
            flight = flightRepository.save(flight);
        }
        return flight;

    }

    @Override
    public boolean delete(long id) {
        if(flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return !flightRepository.existsById(id);
        } else
            return false;
    }


}
