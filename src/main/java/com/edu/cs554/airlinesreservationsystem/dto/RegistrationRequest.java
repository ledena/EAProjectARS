package com.edu.cs554.airlinesreservationsystem.dto;

import com.edu.cs554.airlinesreservationsystem.model.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String userName;
    private final String password;
    private final String password2;
}