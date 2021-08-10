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
    public Reservation makeReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelResesrvation(int reservationId) {
        reservationRepository.deleteById(reservationId);

    }

    @Override
    public List<Reservation> getReservations(int usesrId) {
        return reservationRepository.findAllByUser(usesrId);
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        return reservationRepository.findReservationById(reservationId);
    }


}


