package com.edu.cs554.airlinesreservationsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Airport {
    @Id
    @GeneratedValue
    private int id;
    private String code;
    @Column(name="code", length = 3)
    private String name;
    @Embedded
    private Address address;


}
