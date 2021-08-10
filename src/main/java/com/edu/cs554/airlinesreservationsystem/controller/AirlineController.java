package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("airlines")
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public List<Airline> findAll() {
        return airlineService.findAll();
    }

    @PostMapping
    public Airline save(@RequestBody Airline airport){
        return airlineService.save(airport);
    }

    @PutMapping("{id}")
    public Airline update(@PathVariable long id, @RequestBody Airline airline){
        return airlineService.update(id, airline);
    }

    @DeleteMapping("{id}")
    public Airline delete(@PathVariable long id){
        return airlineService.delete(id);
    }



}