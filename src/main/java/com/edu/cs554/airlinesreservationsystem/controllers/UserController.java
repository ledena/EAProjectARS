package com.edu.cs554.airlinesreservationsystem.controllers;

import com.edu.cs554.airlinesreservationsystem.dto.AuthRequest;
import com.edu.cs554.airlinesreservationsystem.dto.RegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController{

    @Autowired
    private UserService userService;

//    @GetMapping("/test")
//    public String welcome() {
//        return "Welcome to Airlines Reservation System Web Application !!";
//    }
//
//    // Authenticate user by email and password , does the same thing as login
//    @PostMapping("/authenticate")
//    public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) throws Throwable {
//        ResponseEntity<Object> response = userService.authenticate(authRequest);
//        return response;
//    }

    // Registers or Adds user to DB
    @PostMapping("/register")
    public User register(@RequestBody RegistrationRequest request) {
        return userService.register(request);
    }

//    // Retrieves the current logged in user
//    @GetMapping(path = "/current")
//    public ResponseEntity<Object> loggedInUser() {
//        ResponseEntity<Object> user = userService.loggedInUser();
//        return user;
//    }

}
