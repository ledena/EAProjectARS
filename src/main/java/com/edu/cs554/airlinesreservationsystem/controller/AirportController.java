package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("airports")
public class AirportController {

    @Autowired
    public AirportService airportService;

    @GetMapping
    public List<Airport> findAll(){
        return airportService.findAll();
    }

    @PostMapping
    public Airport save(@RequestBody Airport airport){
        return airportService.save(airport);
    }

    @PutMapping("{id}")
    public Airport update(@PathVariable int id, @RequestBody Airport airport){
        return airportService.update(id, airport);
    }

    @DeleteMapping("{id}")
    public Airport delete(@PathVariable int id){
        return airportService.delete(id);
    }



}
