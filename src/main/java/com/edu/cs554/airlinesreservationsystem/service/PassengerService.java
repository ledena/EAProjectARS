package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.PassengerRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.*;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    public List<Passenger> findAll();
    public Optional<Passenger> findById(long passengerId);
    public Passenger update(Passenger passenger);
    public boolean deletePassengerById(long passengerId);
    public Passenger register(PassengerRegistrationRequest request);

}
