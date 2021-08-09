package com.edu.cs554.airlinesreservationsystem.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private int id;
    @Column(name="ticket_number", length = 20)
    private Long number;
    private String reservationCode;
    private LocalDate flightDate;
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;
}
