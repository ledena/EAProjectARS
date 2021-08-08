package com.edu.cs554.airlinesreservationsystem.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Setter
@Getter
@Entity
public class Flight {

    @Id
    @GeneratedValue
    private long id;
    private  int capacity;
    private String number;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

}
