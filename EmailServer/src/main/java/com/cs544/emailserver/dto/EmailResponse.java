package com.cs544.emailserver.dto;

import lombok.Data;

@Data
public class EmailResponse {

    public String message;

    public EmailResponse(String message){
        this.message=message;
    }
}
