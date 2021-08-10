package com.edu.cs554.airlinesreservationsystem;

import com.edu.cs554.airlinesreservationsystem.dto.Mail;
import com.edu.cs554.airlinesreservationsystem.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
@EnableCircuitBreaker
public class AirlinesReservationSystemApplication {

    public static void main(String[] args) {
        ApplicationContext ctx=SpringApplication.run(AirlinesReservationSystemApplication.class, args);

    }

}
