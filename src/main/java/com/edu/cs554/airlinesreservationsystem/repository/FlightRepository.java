package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.model.Airline;
import com.edu.cs554.airlinesreservationsystem.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight,Long> {

//    List<Flight> getAirlinesFlyingOut(Airport departureAirport);
    @Query("select airline from Flight f where f.departureAirport.code=:airportCode")
    List<Airline> getAirlinesFlyingOut(@Param("airportCode") String airportCode);

    @Query("select f from Flight f where f.departureAirport.code=:depCode and f.arrivalAirport.code=:arrCode  and Date(f.departureTime)=Date(:date)")
    List<Flight> findFlights(@Param("depCode") String from, @Param("arrCode") String to, @Param("date") LocalDate date);


}
