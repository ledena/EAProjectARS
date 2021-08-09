package com.edu.cs554.airlinesreservationsystem.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private long id;
    private String firstName;
    private String lastName;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    public Person(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }
}
