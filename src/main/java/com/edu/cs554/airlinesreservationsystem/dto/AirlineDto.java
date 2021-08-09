package com.edu.cs554.airlinesreservationsystem.dto;

import lombok.Data;

@Data
public class AirlineDto {
    private long id;
    private String code;
    private String name;
    private String history;
}
