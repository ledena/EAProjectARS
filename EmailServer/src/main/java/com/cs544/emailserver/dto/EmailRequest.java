package com.cs544.emailserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String from;
    private String to;
    private String message;
    private String subject;
}
