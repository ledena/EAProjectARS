package com.edu.cs554.airlinesreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
public class Agent extends Person{

    public Agent(int id, String firstName, String lastName, User user) {
        super(firstName, lastName, user);
    }

    public Agent() {
    }

}
