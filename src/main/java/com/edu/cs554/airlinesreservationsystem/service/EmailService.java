package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.EmailRequest;
import com.edu.cs554.airlinesreservationsystem.dto.EmailResponse;

public interface EmailService {

    EmailResponse sendEmail(EmailRequest emailRequest);
}
