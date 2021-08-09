package com.edu.cs554.airlinesreservationsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightSearchDto {

    private String from;
    private String to;
    private LocalDate date;

}
