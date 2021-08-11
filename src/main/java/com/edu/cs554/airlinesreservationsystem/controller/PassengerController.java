package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.dto.PassengerPatchRequest;
import com.edu.cs554.airlinesreservationsystem.dto.PassengerRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.PassengerReservationResponseDto;
import com.edu.cs554.airlinesreservationsystem.dto.PassengerUpdateRequest;
import com.edu.cs554.airlinesreservationsystem.model.Passenger;
import com.edu.cs554.airlinesreservationsystem.model.Person;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.repository.PassengerRepository;
import com.edu.cs554.airlinesreservationsystem.repository.PersonRepository;
import com.edu.cs554.airlinesreservationsystem.repository.ReservationRepository;
import com.edu.cs554.airlinesreservationsystem.service.LoginService;
import com.edu.cs554.airlinesreservationsystem.service.PassengerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PASSENGER','ROLE_AGENT')")
@RestController
@RequestMapping(path="/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // Registers or Adds passenger to DB
    @PreAuthorize("permitAll()")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createPassenger(@RequestBody PassengerRegistrationRequest request) {
        return passengerService.create(request);
    }

    @GetMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPassengers() {
        return new ResponseEntity<>(passengerService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = { "/{passengerId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPassenger(@PathVariable long passengerId) throws JSONException {

        JSONObject responseBody = new JSONObject();

        Optional<Passenger> optionalPassenger = passengerService.findById(passengerId);

        if (optionalPassenger.isPresent()) {
            return new ResponseEntity<>(optionalPassenger.get(), HttpStatus.OK);
        } else {
            responseBody.put("success", false);
            responseBody.put("message", "Passenger not found");
            return new ResponseEntity<>(responseBody.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = { "/{passengerId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePassenger(@PathVariable long passengerId, @RequestBody PassengerUpdateRequest passengerUpdateRequest) throws JSONException {

        Optional<Passenger> optionalPassenger = passengerService.findById(passengerId);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (optionalPassenger.isPresent()) {

            Passenger passenger = optionalPassenger.get();

            passenger.setFirstName(passengerUpdateRequest.getFirstName());
            passenger.setLastName(passengerUpdateRequest.getLastName());
            passenger.setDateOfBirth(passengerUpdateRequest.getDateOfBirth());
            passenger.setResidenceAddress(passengerUpdateRequest.getResidenceAddress());
            passenger = passengerService.update(passenger);

            responseBody.put("success", true);
            responseBody.put("message", "Passenger successfully updated");
            responseBody.put("passenger", passenger);

            return new ResponseEntity<>(passenger, httpStatus);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Passenger not found");
            responseBody.put("passengerId", passengerId);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseBody.toString(), httpStatus);

        }
    }

    @PatchMapping(value = { "/{passengerId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchPassenger(@PathVariable long passengerId, @RequestBody PassengerPatchRequest passengerPatchRequest) throws JSONException {

        Optional<Passenger> optionalPassenger = passengerService.findById(passengerId);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (optionalPassenger.isPresent()) {

            Passenger passenger = optionalPassenger.get();

            passenger.setFirstName(passengerPatchRequest.getFirstName());
            passenger.setLastName(passengerPatchRequest.getLastName());
            passenger.setDateOfBirth(passengerPatchRequest.getDateOfBirth());
            passenger = passengerService.update(passenger);

            responseBody.put("success", true);
            responseBody.put("message", "Passenger successfully updated");
            responseBody.put("passenger", passenger);

            return new ResponseEntity<>(passenger, httpStatus);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Passenger not found");
            responseBody.put("passengerId", passengerId);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseBody.toString(), httpStatus);

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PASSENGER')")
    @DeleteMapping(value = { "/{passengerId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePassenger(@PathVariable long passengerId) throws JSONException {

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (passengerService.deletePassengerById(passengerId)) {

            responseBody.put("success", true);
            responseBody.put("message", "Successfully deleted");
            responseBody.put("passengerId", passengerId);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Unable to delete: Error");
            responseBody.put("passengerId", passengerId);
            httpStatus = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity<>(responseBody.toString(), httpStatus);
    }

//    @PreAuthorize("permitAll()")
//    @GetMapping("/{id}/reservations")
//    public Reservation getPassengerReservationById(@PathVariable int id){
//        User user = loginService.loggedInUser();
//        Passenger passenger= (Passenger) personRepository.findAllByUser(user);
//
//        return reservationRepository.findAllByPassengerAndId(passenger, id);
//
//    }

    @PreAuthorize("permitAll()")
    @GetMapping("/reservations/{id}")
    public PassengerReservationResponseDto getPassengerReservationById(@PathVariable int id){
        User user = loginService.loggedInUser();
        Passenger passenger= (Passenger) personRepository.findAllByUser(user);

//        return reservationRepository.findAllByPassengerAndId(passenger, id);
        return passengerService.findPassengerReservationById(passenger, id);

    }

    @PreAuthorize("permitAll()")
    @GetMapping("/reservations")
    public List<PassengerReservationResponseDto> getPassengerReservations(){
        User user = loginService.loggedInUser();
        Passenger passenger= (Passenger) personRepository.findAllByUser(user);

        return passengerService.findPassengerReservations(passenger);

    }
}
