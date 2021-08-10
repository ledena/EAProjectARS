package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    public abstract List<Reservation> findAllByUser(int userId);
    public abstract Reservation findReservationById(int reservationId);

}
