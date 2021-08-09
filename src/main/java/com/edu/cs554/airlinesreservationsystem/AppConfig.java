package com.edu.cs554.airlinesreservationsystem;

import com.edu.cs554.airlinesreservationsystem.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class AppConfig {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
        @Bean
        public ListMapper listMapper(){
            return new ListMapper();
        }
}
