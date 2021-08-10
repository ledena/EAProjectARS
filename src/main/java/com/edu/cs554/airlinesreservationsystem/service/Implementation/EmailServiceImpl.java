package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.EmailRequest;
import com.edu.cs554.airlinesreservationsystem.dto.EmailResponse;
import com.edu.cs554.airlinesreservationsystem.service.EmailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service("mailService")
public class EmailServiceImpl implements EmailService {
//


    @Autowired
    JavaMailSender mailSender;

    @Value("${spring-boot-server.name}")
    private String serverName;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    @HystrixCommand(fallbackMethod = "fallBack")
    public EmailResponse sendEmail(EmailRequest emailRequest) {
        String url = getBaseServiceUrl() + "/email";
        return restTemplate
                .postForObject(url,emailRequest,EmailResponse.class);

    }

    public EmailResponse fallBack(EmailRequest emailRequest) {
        return new EmailResponse();
    }


    private String getBaseServiceUrl() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
        serviceInstances.forEach(System.out::println);
        return serviceInstances.get(0).getUri().toString();
    }

}
