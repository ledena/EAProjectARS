package com.edu.cs554.airlinesreservationsystem.dto;

import com.edu.cs554.airlinesreservationsystem.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReservationRequest {

    private String reservationCode;
    private long idPassenger;
    private List<Long> idflights=new ArrayList<>();
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //private LocalDate reservationTime;
    //private int idReservedBy;
    //private List<Ticket> tickets=new ArrayList<>();
    private Status status;

}
