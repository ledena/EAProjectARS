package com.edu.cs554.airlinesreservationsystem.dto;

import com.edu.cs554.airlinesreservationsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AdminRegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final Address residenceAddress;
    private final RegistrationRequest user;
}