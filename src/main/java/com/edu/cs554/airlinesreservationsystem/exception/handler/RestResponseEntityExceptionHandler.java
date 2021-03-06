package com.edu.cs554.airlinesreservationsystem.exception.handler;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private HttpHeaders headers;

    @ExceptionHandler({DataIntegrityViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleDataIntegrityViolationException(Exception exception, HttpServletRequest request) throws JSONException {

        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject responseBody = new JSONObject();

        responseBody.put("success", false);
        responseBody.put("message", "Already exist: Error");

        return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, HttpServletRequest request) throws JSONException {

        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject responseBody = new JSONObject();

        responseBody.put("success", false);
        responseBody.put("message", "ACCESS FORBIDDEN: Error");

        return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.FORBIDDEN);
    }

}
