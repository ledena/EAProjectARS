package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.PassengerRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.*;
import com.edu.cs554.airlinesreservationsystem.repositories.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserService userService;

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> findById(long passengerId) {
        return passengerRepository.findById(passengerId);
    }

    public Passenger update(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public boolean deletePassengerById(long passengerId) {
        if(passengerRepository.existsById(passengerId)) {
            passengerRepository.deleteById(passengerId);
            return !passengerRepository.existsById(passengerId);
        } else
            return false;
    }

    public Passenger register(PassengerRegistrationRequest request) {

        User user = new User(request.getUser().getUserName(), request.getUser().getPassword(), Role.PASSENGER);

        user = userService.encodePassword(user);

        Passenger passenger = new Passenger(request.getFirstName(), request.getLastName(), request.getDateOfBirth(), request.getResidenceAddress(), user);

        return passengerRepository.save(passenger);

    }

}
