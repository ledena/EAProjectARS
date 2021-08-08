package com.edu.cs554.airlinesreservationsystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SecondaryTable(name="history")
public class Airline {

    @Id
    @GeneratedValue
    private long id;
    private String code;
    private String name;
    @Column(table="history", length = 2000)
    private String history;



}
