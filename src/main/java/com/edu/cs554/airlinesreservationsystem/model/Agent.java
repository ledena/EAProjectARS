package com.edu.cs554.airlinesreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Agent extends Person{

    public Agent(String firstName, String lastName, Address residenceAddress, User user) {
        super(firstName, lastName, residenceAddress, user);
    }

}
