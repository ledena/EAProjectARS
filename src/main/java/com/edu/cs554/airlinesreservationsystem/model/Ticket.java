package com.edu.cs554.airlinesreservationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Ticket {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private int id;
    @Column(name="ticket_number", length = 20)
    private String number;
    private String reservationCode;
    private LocalDateTime flightDate;
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    public Ticket(String number, String reservationCode, LocalDateTime flightDate, String flightNumber, Reservation reservation) {
        this.number = number;
        this.reservationCode = reservationCode;
        this.flightDate = flightDate;
        this.flightNumber = flightNumber;
        this.reservation = reservation;
    }

}
