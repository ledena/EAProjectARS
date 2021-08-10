package com.edu.cs554.airlinesreservationsystem.controller;


import com.edu.cs554.airlinesreservationsystem.dto.ReservationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import com.edu.cs554.airlinesreservationsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> findAll() {
       return reservationService.findAll();
    }

    @PostMapping
    public Reservation save(@RequestBody ReservationRequest reservation){
        return reservationService.save(reservation);
    }

    @PatchMapping("{id}")
    public Reservation update(@PathVariable int id, @RequestBody ReservationRequest reservation){
        return reservationService.update(id, reservation);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable int id){
        return reservationService.delete(id);
    }

}
