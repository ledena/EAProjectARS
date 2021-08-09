package com.edu.cs554.airlinesreservationsystem.dto;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoggedInUserRequest {
    private String email;
}
