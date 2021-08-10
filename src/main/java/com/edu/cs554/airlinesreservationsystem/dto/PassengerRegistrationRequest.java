package com.edu.cs554.airlinesreservationsystem.dto;

import com.edu.cs554.airlinesreservationsystem.model.Address;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PassengerRegistrationRequest {
    private final String firstName;
    private final String lastName;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private final LocalDate dateOfBirth;
    private final Address residenceAddress;
    private final RegistrationRequest user;
    private final String email;
}