package com.edu.cs554.airlinesreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
public class Admin extends Person{

    public Admin(String firstName, String lastName, User user) {
        super(firstName, lastName, user);
    }

    public Admin() {
    }

}
