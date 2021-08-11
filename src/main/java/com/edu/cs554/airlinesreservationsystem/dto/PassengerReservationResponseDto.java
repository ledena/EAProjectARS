package com.edu.cs554.airlinesreservationsystem.dto;

import com.edu.cs554.airlinesreservationsystem.model.Flight;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PassengerReservationResponseDto {

    private String reservationCode;
    private String passengerFirstName;
    private String passengerLastName;
    private String passengerEmail;
    private List<Flight> flights;



}
