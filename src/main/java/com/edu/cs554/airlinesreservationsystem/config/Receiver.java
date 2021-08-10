package com.edu.cs554.airlinesreservationsystem.config;

import com.edu.cs554.airlinesreservationsystem.dto.EmailRequest;
import com.edu.cs554.airlinesreservationsystem.dto.EmailResponse;
import com.edu.cs554.airlinesreservationsystem.dto.Mail;
import com.edu.cs554.airlinesreservationsystem.service.EmailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class Receiver {
    @Autowired
    public EmailService emailService;


    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(EmailRequest emailRequest) throws InterruptedException {
        emailService.sendEmail(emailRequest);
        System.out.println("Finished sending the email: <>");
    }



}
