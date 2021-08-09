package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.dto.AuthRequest;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.service.LoginService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
@Transactional
public class UserController{

    @Autowired
    private LoginService loginService;

    // Authenticate user by userName and password , does the same thing as login
    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws JSONException {

        String token = loginService.authenticate(authRequest);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (token == null) {

            responseBody.put("success", false);
            responseBody.put("message", "Authentication invalid");
            httpStatus = HttpStatus.BAD_REQUEST;

        } else {

            responseBody.put("success",true);
            responseBody.put("message", "Authentication successfully");
            responseBody.put("token","Bearer " + token);
        }

        return new ResponseEntity<>(responseBody.toString(), httpStatus);
    }

    // Retrieves the current logged in user
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> loggedInUser() {
        User user = loginService.loggedInUser();

        JSONObject responseBody = new JSONObject();

        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            try {
                responseBody.put("success",false);
                responseBody.put("message", "No user found");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }

}
