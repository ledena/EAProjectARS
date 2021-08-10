package com.cs544.emailserver.service;

import com.cs544.emailserver.dto.EmailRequest;
import com.cs544.emailserver.dto.EmailResponse;
import com.cs544.emailserver.dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;
    public void sendEmail(EmailRequest emailRequest) {
        System.out.println("@@@@@@@@ inside service"+emailRequest);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(emailRequest.getSubject());
            mimeMessageHelper.setFrom(new InternetAddress(emailRequest.getFrom(), "EA Airline Reservation"));
            mimeMessageHelper.setTo(emailRequest.getTo());
            mimeMessageHelper.setText(emailRequest.getMessage());

            mailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
