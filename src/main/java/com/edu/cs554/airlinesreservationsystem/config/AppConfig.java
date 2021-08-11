package com.edu.cs554.airlinesreservationsystem.config;

import com.edu.cs554.airlinesreservationsystem.dto.Mail;
import com.edu.cs554.airlinesreservationsystem.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpHeaders;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Configuration
@EnableScheduling
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ListMapper listMapper(){
        return new ListMapper();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }

    @Bean
    public Mail mail(){ return new Mail();}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


//    @Autowired
//    private Environment env;
//
//    @Bean
//    public JavaMailSender getMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost(env.getProperty("spring.mail.host"));
//        mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
//        mailSender.setUsername(env.getProperty("spring.mail.username"));
//        mailSender.setPassword(env.getProperty("spring.mail.password"));
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.starttls.enable", "true");
//        javaMailProperties.put("mail.smtp.auth", "true");
//        javaMailProperties.put("mail.transport.protocol", "smtp");
//        javaMailProperties.put("mail.debug", "true");
//
//        mailSender.setJavaMailProperties(javaMailProperties);
//        return mailSender;
//    }

//    @Bean
//    public JavaMailSenderImpl mailSender(@Value("${app.server.protocol}") String protocol,
//                                         @Value("${app.server.host}") String serverHost){
//
//    }

}
