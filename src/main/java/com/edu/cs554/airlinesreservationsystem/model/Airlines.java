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
@SecondaryTable(name="airline_history",
        pkJoinColumns=@PrimaryKeyJoinColumn(name="history"))
public class Airlines {

    @Id
    @GeneratedValue
    private long id;
    private String code;
    private String name;


    private String history;



}
