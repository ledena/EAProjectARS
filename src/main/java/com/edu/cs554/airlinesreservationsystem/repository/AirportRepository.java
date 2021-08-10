package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AirportRepository extends JpaRepository<Airport,Integer> {
    Airport getByCode(String code);

    Airport findAirlineByCode(String code);


}
