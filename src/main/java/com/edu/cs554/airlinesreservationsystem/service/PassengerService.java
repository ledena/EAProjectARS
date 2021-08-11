package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.PassengerRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.PassengerReservationResponseDto;
import com.edu.cs554.airlinesreservationsystem.model.*;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    Passenger create(PassengerRegistrationRequest request);
    List<Passenger> findAll();
    Optional<Passenger> findById(long passengerId);
    Passenger update(Passenger passenger);
    boolean deletePassengerById(long passengerId);

    PassengerReservationResponseDto findPassengerReservationById(Passenger passenger, int id);

    List<PassengerReservationResponseDto> findPassengerReservations(Passenger passenger);
}
