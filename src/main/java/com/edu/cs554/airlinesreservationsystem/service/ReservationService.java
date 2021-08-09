package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
}
