package com.edu.cs554.airlinesreservationsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="ticket_number")
    private Long number;
    private String reservationCode;
    private LocalDate flightDate;
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;
}
