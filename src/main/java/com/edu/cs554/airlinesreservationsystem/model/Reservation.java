package com.edu.cs554.airlinesreservationsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate reservationTime;
    private String reservationCode;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "reservation")
    private List<Ticket> tickets=new ArrayList<>();
    @ManyToOne
    private Passenger passenger;
    @Enumerated(EnumType.STRING)
    private Status status;
//    @ManyToMany(mappedBy = "reservations")
//    private List<Flight> flights=new ArrayList<>();
    /////// add flight reference


}
