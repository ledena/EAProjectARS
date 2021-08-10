package com.edu.cs554.airlinesreservationsystem.dto;

import com.edu.cs554.airlinesreservationsystem.model.Address;
import com.edu.cs554.airlinesreservationsystem.model.Flight;
import com.edu.cs554.airlinesreservationsystem.model.Status;
import com.edu.cs554.airlinesreservationsystem.model.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReservationUpdatePatch {
    private final Status status;
    private final List<Flight> flights=new ArrayList<>();
    private final List<Ticket> tickets=new ArrayList<>();

}
