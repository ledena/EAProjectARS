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


//    //make reservation
//    @PostMapping(value = "/make", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Reservation makeReservation(@RequestBody ReservationRequest request) {
//
//         Reservation newReservation=new Reservation();
//
//    }
//
//
    //view and confirm reservation

    @PatchMapping(value = { "/{currentReservation}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmReservation(@PathVariable Reservation currentReservation,@RequestBody ReservationUpdatePatch reservationUpdatePatch) throws JSONException {


        Reservation reservation = reservationService.getReservationById(currentReservation);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (reservation!=null) {

           Status reservationStatus= reservation.getStatus();

            if(reservationStatus.equals(Status.RESERVED))
            {
              reservation.setStatus(Status.CONFIRMED);
            }

            reservation.setStatus(reservationUpdatePatch.getStatus());
            reservation=reservationService.update(reservation);

            responseBody.put("success", true);
            responseBody.put("message", "Reservation Successfully Confirmed");
            responseBody.put("reservation", reservation);

            return new ResponseEntity<>(reservation, httpStatus);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Confirmation Failed");
            responseBody.put("reservationId", currentReservation);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseBody.toString(), httpStatus);

        }
    }


    //cancel reservation

//    @PatchMapping(value = { "/{reservationId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> cancelReservation(@RequestBody ReservationUpdatePatch reservationUpdatePatch) throws JSONException {
//
//        Reservation reservation = reservationService.getReservationById(reservationId);
//
//        JSONObject responseBody = new JSONObject();
//        HttpStatus httpStatus = HttpStatus.OK;
//
//        if (reservation!=null) {
//
//            Status reservationStatus= reservation.getStatus();
//
//            if(reservationStatus.equals(Status.RESERVED)|| reservationStatus.equals(Status.CONFIRMED))
//            {
//                reservation.setStatus(Status.CANCELLED);
//            }
//
//            reservation.setStatus(reservationUpdatePatch.getStatus());
//            reservation=reservationService.update(reservation);
//
//            responseBody.put("success", true);
//            responseBody.put("message", "Reservation Successfully  Cancelled");
//            responseBody.put("reservation", reservation);
//
//            return new ResponseEntity<>(reservation, httpStatus);
//
//        } else {
//
//            responseBody.put("success", false);
//            responseBody.put("message", "Confirmation Cancelled");
//            responseBody.put("reservationId", reservationId);
//            httpStatus = HttpStatus.BAD_REQUEST;
//            return new ResponseEntity<>(responseBody.toString(), httpStatus);
//
//        }
//    }
//

}
