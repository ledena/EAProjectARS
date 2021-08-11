package com.edu.cs554.airlinesreservationsystem.controller;


import com.edu.cs554.airlinesreservationsystem.dto.PassengerPatchRequest;
import com.edu.cs554.airlinesreservationsystem.dto.PassengerRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.ReservationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.ReservationUpdatePatch;
import com.edu.cs554.airlinesreservationsystem.model.*;
import com.edu.cs554.airlinesreservationsystem.service.LoginService;
import com.edu.cs554.airlinesreservationsystem.service.ReservationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private LoginService loginService;

//    @GetMapping
//    public List<Reservation> findAll() {
//       return reservationService.findAll();
//    }


  //list of reservations made by a user a passanger or agent
    @GetMapping
    public List<Reservation> listReservations() {
        User loggedInUser=new User();
        loggedInUser=loginService.loggedInUser();
        int userId=loggedInUser.getId();

        return reservationService.getReservations(loggedInUser);
    }
//    @GetMapping
//    public List<Reservation> listReservations() {
//        User loggedInUser=new User();
//        loggedInUser=loginService.loggedInUser();
//        int userId=loggedInUser.getId();
//
//        return reservationService.getReservations(userId);
//    }

    @GetMapping(value = { "/listReservations" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservationsList(@PathVariable long passengerId) throws JSONException {

        JSONObject responseBody = new JSONObject();

        User loggedInUser=new User();
        loggedInUser=loginService.loggedInUser();
       // int userId=loggedInUser.getId();


        List<Reservation> listReservations = reservationService.getReservations(loggedInUser);

        if (!listReservations.isEmpty()) {
            return new ResponseEntity<>(listReservations.get(0), HttpStatus.OK);
        } else {
            responseBody.put("success", false);
            responseBody.put("message", "No reservations found");
            return new ResponseEntity<>(responseBody.toString(), HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping(value = "/makeNewRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation makeReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @PostMapping
    public Reservation save(@RequestBody ReservationRequest reservation){
        User loggedInUser=new User();
        loggedInUser=loginService.loggedInUser();
        return reservationService.save(reservation,loggedInUser);
    }

    @PatchMapping("{id}")
    public Reservation update(@PathVariable int id, @RequestBody ReservationRequest reservation){
        return reservationService.update(id, reservation);
    }
    @PatchMapping("cancelReservation/{id}")
    public Reservation cancel(@PathVariable int id){
        return reservationService.cancelReservation(id);
    }

    @PatchMapping("confirmReservation/{id}")
    public Reservation confirmReservation(@PathVariable int id){
        System.out.println("####################### in controller");
        return reservationService.confirmReservation(id);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable int id){
        return reservationService.delete(id);
    }


}
