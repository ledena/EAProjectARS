package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.ReservationRequest;
import com.edu.cs554.airlinesreservationsystem.exception.ResourceNotFoundException;
import com.edu.cs554.airlinesreservationsystem.model.*;
import com.edu.cs554.airlinesreservationsystem.repository.PassengerRepository;
import com.edu.cs554.airlinesreservationsystem.repository.ReservationRepository;
import com.edu.cs554.airlinesreservationsystem.repository.UserRepository;
import com.edu.cs554.airlinesreservationsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation save(ReservationRequest newReservation) {
        Passenger newPassenger = passengerRepository.findById(newReservation.getIdPassenger())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not exist with id :" + newReservation.getIdPassenger()));
        //User newUser = userRepository.findById(newReservation.getIdReservedBy())
        //        .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + newReservation.getIdReservedBy()));
        User newReservedBy = userRepository.findById(newReservation.getIdReservedBy());

        Reservation reservation = new Reservation(newReservation.getReservationCode(),
                                                    newPassenger,
                                                    newReservation.getReservationTime(),
                                                    newReservedBy,
                                                    Status.RESERVED);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(int id, ReservationRequest newReservation) {
        Optional<Reservation> optionalReservation = Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not exist with id :" + id)));

        Reservation reservation = optionalReservation.get();
        if(optionalReservation.isPresent()) {
            Passenger newPassenger = passengerRepository.findById(newReservation.getIdPassenger())
                    .orElseThrow(() -> new ResourceNotFoundException("Passenger not exist with id :" + newReservation.getIdPassenger()));
            User newReservedBy = userRepository.findById(newReservation.getIdReservedBy());
            reservation.setReservationCode(newReservation.getReservationCode());
            reservation.setPassenger(newPassenger);
            reservation.setReservationTime(newReservation.getReservationTime());
            reservation.setReservedBy(newReservedBy);
            reservation.setStatus(newReservation.getStatus());
            reservation = reservationRepository.save(reservation);
        }
        return reservation;
    }

    @Override
    public boolean delete(int id) {
        if(reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return !reservationRepository.existsById(id);
        } else
            return false;
    }
}
