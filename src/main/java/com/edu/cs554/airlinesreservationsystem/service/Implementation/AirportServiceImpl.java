package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.exception.ResourceNotFoundException;
import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.repository.AirportRepository;
import com.edu.cs554.airlinesreservationsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport update(int id, Airport newAirport) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not exist with id :" + id));

        airport.setCode(newAirport.getCode());
        airport.setName(newAirport.getName());
        airport.setAddress(newAirport.getAddress());

        Airport updateAirport = airportRepository.save(airport);
        return updateAirport;
    }

    @Override
    public Airport delete(int id) {
        Airport delAirport = airportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not exist with id :" + id));

        airportRepository.delete(delAirport);

        return delAirport;
    }


}
