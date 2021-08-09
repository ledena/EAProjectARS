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

    @Embedded
    private Address residenceAddress;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    public Person(String firstName, String lastName, Address residenceAddress, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.residenceAddress = residenceAddress;
        this.user = user;
    }

}
