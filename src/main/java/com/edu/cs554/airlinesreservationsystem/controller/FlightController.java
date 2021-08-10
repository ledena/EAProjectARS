package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.dto.FlightPassengerResponseDto;
import com.edu.cs554.airlinesreservationsystem.dto.FlightRequest;
import com.edu.cs554.airlinesreservationsystem.dto.FlightSearchDto;
import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Flight;
import com.edu.cs554.airlinesreservationsystem.service.FlightService;
import com.edu.cs554.airlinesreservationsystem.util.ListMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    public ModelMapper mapper;

    @Autowired
    public ListMapper listMapper;


    @GetMapping()
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    @GetMapping("out/airlines")
    public List<Airline> getAirlinesFlyingOutFromAirport(@RequestParam String airportCode) {
        System.out.println("airportCode" + airportCode);
        return flightService.getAirlinesFlyingOut(airportCode);
    }

    @PostMapping("search")
    public List<FlightPassengerResponseDto> getFlights(@RequestBody FlightSearchDto flightSearchDto) {
        List<Flight> flights = flightService.findFlights(flightSearchDto);
        return listMapper.mapList(flights, FlightPassengerResponseDto.class);
    }

    @PostMapping
    public Flight save(@RequestBody FlightRequest flight) {
        return flightService.save(flight);
    }

    @PatchMapping("{id}")
    public Flight update(@PathVariable long id, @RequestBody FlightRequest flightRequestReq) {
        return flightService.update(id, flightRequestReq);
    }

    @DeleteMapping(value = {"{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id) throws JSONException {

        //return flightService.delete(id);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (flightService.delete(id)) {

            responseBody.put("success", true);
            responseBody.put("message", "Successfully deleted");
            responseBody.put("flight", id);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Unable to delete: Error");
            responseBody.put("adminId", id);
            httpStatus = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity<>(responseBody.toString(), httpStatus);
    }
}
