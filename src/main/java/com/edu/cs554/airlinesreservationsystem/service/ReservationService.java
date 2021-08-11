package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.ReservationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.model.Passenger;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import com.edu.cs554.airlinesreservationsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAll();
    //public abstract Reservation makeReservation(Reservation reservation);
   // public abstract void cancelResesrvation(int reservationId);
    void sendReservationReminder();

    public abstract Reservation makeReservation(Reservation reservation);
    public abstract void cancelResesrvation(int reservationId);
    public abstract List<Reservation> getReservations(int userId);
    public abstract Reservation getReservationById(int reservationId);
    Reservation save(ReservationRequest reservation);
    Reservation update(int id, ReservationRequest reservation);
    boolean delete(int id);


    public abstract List<Reservation> getReservations( User userId);
   Reservation update(Reservation reservation);
   Reservation getReservationById(Reservation reservationId);







}
