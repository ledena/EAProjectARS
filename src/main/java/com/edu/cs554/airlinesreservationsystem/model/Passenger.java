package com.edu.cs554.airlinesreservationsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends Person{

    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateOfBirth;

    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, Address residenceAddress, User user) {
        super(firstName, lastName, residenceAddress, user);
        this.dateOfBirth = dateOfBirth;
    }
}
