package com.edu.cs554.airlinesreservationsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roleList;

}
