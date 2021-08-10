package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.exception.ResourceNotFoundException;
import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.repository.AirlineRepository;
import com.edu.cs554.airlinesreservationsystem.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Service
//@Transactional
//public class AirlineServiceImpl extends BaseReadWriteServiceImpl{
//}
@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Airline update(long id, Airline newAirline) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airline not exist with id :" + id));

        airline.setCode(newAirline.getCode());
        airline.setName(newAirline.getName());
        airline.setHistory(newAirline.getHistory());

        Airline updateAirline = airlineRepository.save(airline);
        return updateAirline;
    }

    @Override
    public Airline delete(long id) {
        Airline delAirline = airlineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airline not exist with id :" + id));

        airlineRepository.delete(delAirline);
        return delAirline;
    }
}
