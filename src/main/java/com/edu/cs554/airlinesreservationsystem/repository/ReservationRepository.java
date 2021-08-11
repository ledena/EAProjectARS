package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.dto.PassengerReservationResponseDto;
import com.edu.cs554.airlinesreservationsystem.model.Passenger;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import com.edu.cs554.airlinesreservationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

//    public abstract List<Reservation> findAllByUser(User userId);
    public abstract Reservation findReservationById(Reservation reservationId);

    @Query("SELECT distinct f.reservations FROM Flight f JOIN  f.reservations r WHERE r.status ='CONFIRMED' and Date(f.departureTime)=Date(:date) and hour(f.departureTime)=hour(:date) and minute(departureTime)=minute(:date) ")
    Optional<List<Reservation>> getReservationsForReminder(@Param("date") LocalDateTime date);
    public abstract List<Reservation> findAllByReservedBy(User user);
    Reservation findReservationById(int id);
    Reservation findAllByPassengerAndId(Passenger passenger, int id);
    List<Reservation> findAllByPassenger(Passenger passenger);
}
