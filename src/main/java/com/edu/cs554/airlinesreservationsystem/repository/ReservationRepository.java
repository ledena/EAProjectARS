package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
