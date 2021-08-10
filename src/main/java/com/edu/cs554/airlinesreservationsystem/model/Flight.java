package com.edu.cs554.airlinesreservationsystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    private long id;
    private String number;
    private  int capacity;
    @ManyToOne
    private Airline airline;
    private LocalDateTime departureTime;
    @ManyToOne
    private Airport departureAirport;
    private LocalDateTime arrivalTime;
    @ManyToOne
    private Airport arrivalAirport;
    @ManyToMany
    private List<Reservation> reservations = new ArrayList<>();

    public Flight(String number, int capacity, Airline airline, LocalDateTime departureTime, Airport departureAirport, LocalDateTime arrivalTime, Airport arrivalAirport) {
        this.number = number;
        this.capacity = capacity;
        this.airline = airline;
        this.departureTime = departureTime;
        this.departureAirport = departureAirport;
        this.arrivalTime = arrivalTime;
        this.arrivalAirport = arrivalAirport;
    }
}
