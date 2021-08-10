package com.edu.cs554.airlinesreservationsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightRequest {
    private String number;
    private int capacity;
    private long idAirline;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;
    private int idDepartureAirport;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;
    private int idArrivalAirport;

}
//yyyy-mm-dd hh:mm:ss