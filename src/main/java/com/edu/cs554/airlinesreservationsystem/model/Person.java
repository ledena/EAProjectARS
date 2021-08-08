package com.edu.cs554.airlinesreservationsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Person {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;


}
