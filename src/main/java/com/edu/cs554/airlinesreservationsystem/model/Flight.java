package com.edu.cs554.airlinesreservationsystem.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
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

}
