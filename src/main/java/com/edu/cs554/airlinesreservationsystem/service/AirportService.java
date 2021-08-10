package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.model.Airport;

import java.util.List;

public interface AirportService {
        List<Airport> findAll();
        Airport save(Airport airport);
        Airport update(int id, Airport airport);
        Airport delete(int id);

        }
