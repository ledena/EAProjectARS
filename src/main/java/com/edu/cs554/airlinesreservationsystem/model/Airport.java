package com.edu.cs554.airlinesreservationsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Airport {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="code", length = 3)
    private String code;
    
    private String name;
    @Embedded
    private Address address;


}
