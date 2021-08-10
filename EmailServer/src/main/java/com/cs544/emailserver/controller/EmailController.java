package com.cs544.emailserver.controller;

import com.cs544.emailserver.dto.EmailRequest;
import com.cs544.emailserver.dto.Mail;
import com.cs544.emailserver.dto.EmailResponse;
import com.cs544.emailserver.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping
    public EmailResponse sendEmail(@RequestBody EmailRequest emailRequest){
        try{
            System.out.println("**************** inside server controller");
            emailService.sendEmail(emailRequest);
            return new EmailResponse("Email Sent Successfully");
        }
        catch (Exception ex){
            return new EmailResponse("Failed To Send Email");
        }
    }
}
