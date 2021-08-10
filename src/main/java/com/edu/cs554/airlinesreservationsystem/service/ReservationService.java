package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.ReservationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;

import java.util.List;

public interface ReservationService {
    abc
    List<Reservation> findAll();
    void sendReservationReminder();
    public abstract Reservation makeReservation(Reservation reservation);
    public abstract void cancelResesrvation(int reservationId);
    public abstract List<Reservation> getReservations(int userId);
    public abstract Reservation getReservationById(int reservationId);
    


}
