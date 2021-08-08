package com.edu.cs554.airlinesreservationsystem.model;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
public class Passenger extends Person{

    private LocalDate dateOfBirth;
    @Embedded
    private Address residenceAddress;
}
