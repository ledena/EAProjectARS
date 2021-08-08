package com.edu.cs554.airlinesreservationsystem.config;

import edu.miu.common.config.CommonServiceConfig;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

public class AppConfig extends CommonServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
