package com.edu.cs554.airlinesreservationsystem.exception.handler;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ DataIntegrityViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleDataIntegrityViolationException(Exception exception, WebRequest request) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject responseBody = new JSONObject();
        responseBody.put("success", false);
        responseBody.put("message", "Already exist: Error");
        return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.BAD_REQUEST);
    }

}
