package com.edu.cs554.airlinesreservationsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private String from;
    private String to;
    private String message;
    private String subject;
}
