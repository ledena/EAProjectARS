package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.EmailRequest;
import com.edu.cs554.airlinesreservationsystem.dto.Mail;
import com.edu.cs554.airlinesreservationsystem.dto.ReservationRequest;
import com.edu.cs554.airlinesreservationsystem.exception.ResourceNotFoundException;
import com.edu.cs554.airlinesreservationsystem.model.*;
import com.edu.cs554.airlinesreservationsystem.repository.*;
import com.edu.cs554.airlinesreservationsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Mail mail;


    @Value("${email.from}")
    private String emailFrom;


    private Optional<List<Reservation>> reservations;


    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
//    @Scheduled(fixedRate = 60000)
    public void sendReservationReminder() {
        LocalDateTime date= LocalDateTime.now();
        LocalDateTime tomorrow = date.plusDays(1);
        Optional<List<Reservation>> reservations = reservationRepository.getReservationsForReminder(tomorrow);
        System.out.println("**********"+tomorrow);

        if (reservations.isPresent()) {
            for(Reservation reservation:reservations.get()){
                Passenger passenger=reservation.getPassenger();
                String message="Dear "+passenger.getFirstName()+" "+passenger.getLastName()+"\n\nYour Flight leaves in 24 hours!!!\n\nThanks";
                EmailRequest emailRequest=new EmailRequest(emailFrom, passenger.getEmail(), message,"Flight Reservation Reminder" );
                // Send a message with a POJO - the template reuse the message converter
                System.out.println("Sending an email message.");
                jmsTemplate.convertAndSend("mailbox", emailRequest);
                System.out.println("Finished putting the email in the queue");
            }
        }


    }

    @Override
    public Reservation createReservation(ReservationRequest request) {
        return null;
    }


    public Reservation makeReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelResesrvation(int reservationId) {

    }

    @Override
    public List<Reservation> getReservations(int userId) {
        return null;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        return null;
    }

//    @Override
//    public void cancelReservation(int reservationId) {
//        reservationRepository.deleteById(reservationId);
//    }

    public List<Reservation> getReservations(User userId) {
        return reservationRepository.findAllByReservedBy(userId);
    }

    @Override
    public Reservation updateStatus(Reservation reservation) {
        //return reservationRepository.findReservationById(reservation.getId());
        return reservationRepository.save(reservation);

    }


    /*@Override
    public Reservation getReservationById(Reservation id) {
        return reservationRepository.findByReservationId(id.getId());
    }*/


    @Override
    public Reservation save(ReservationRequest newReservation, User loggedInUser) {
        Passenger newPassenger = passengerRepository.findById(newReservation.getIdPassenger())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not exist with id :" + newReservation.getIdPassenger()));
        //User newUser = userRepository.findById(newReservation.getIdReservedBy())
        //        .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + newReservation.getIdReservedBy()));
        //User newReservedBy = userRepository.findById(newReservation.getIdReservedBy());

        LocalDate dateNow = LocalDate.now();
        Reservation reservation = new Reservation(newReservation.getReservationCode(),
                newPassenger,
                dateNow,
                loggedInUser,
                Status.RESERVED);
        for (Long flightId : newReservation.getIdflights()){
            Flight flight = flightRepository.findById(flightId)
                    .orElseThrow(() -> new ResourceNotFoundException("This flight not exist with id :" + flightId));
            reservation.addFlight(flight);
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(int id, ReservationRequest newReservation) {
        Optional<Reservation> optionalReservation = Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not exist with id :" + id)));
        Reservation reservation = optionalReservation.get();
        if (optionalReservation.isPresent()) {
            Passenger newPassenger = passengerRepository.findById(newReservation.getIdPassenger())
                    .orElseThrow(() -> new ResourceNotFoundException("Passenger not exist with id :" + newReservation.getIdPassenger()));
            //User newReservedBy = userRepository.findById(newReservation.getIdReservedBy());
            reservation.setReservationCode(newReservation.getReservationCode());
            reservation.setPassenger(newPassenger);
            //reservation.setReservationTime(newReservation.getReservationTime());
            //reservation.setReservedBy(newReservedBy);
            reservation.setStatus(newReservation.getStatus());
            reservation = reservationRepository.save(reservation);
        }
        return reservation;
    }

    @Override
    public boolean delete(int id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return !reservationRepository.existsById(id);
        } else
            return false;
    }

    @Override
    public Reservation cancelReservation(int id) {
        Optional<Reservation> optionalReservation = Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not exist with id :" + id)));
        Reservation reservation = optionalReservation.get();
        if (optionalReservation.isPresent()) {
            Status reservationStatus= reservation.getStatus();

            if(reservationStatus.equals(Status.RESERVED) || reservationStatus.equals(Status.CONFIRMED)){
                reservation.setStatus(Status.CANCELLED);
                reservation = reservationRepository.save(reservation);
            }
        }
        return reservation;
    }

    @Override
    public Reservation confirmReservation(int id) {

        Reservation reservation = reservationRepository.findReservationById(id);

        //Reservation reservation = optionalReservation;
        if (reservation != null) {
            Status reservationStatus= reservation.getStatus();

            if(reservationStatus.equals(Status.RESERVED) ){
                reservation.setStatus(Status.CONFIRMED);
                System.out.println("####################### in Service"+reservation.getFlights());
                for (Flight flight : reservation.getFlights()){
                    System.out.println("####################### in Service");
                    Random r = new Random();
                    String val="";
                    long number = 1000000000L + (int)(r.nextFloat() * 899900000);
                    long number2=1000000000L + (int)(r.nextFloat() * 899900000);
                    val += String.valueOf(number);
                    val += String.valueOf(number2);


                    Ticket ticket = new Ticket(val,
                    reservation.getReservationCode(),flight.getDepartureTime() ,
                    flight.getNumber(),reservation
                    );
                    reservation.addTickets(ticket);
                    ticketRepository.save(ticket);

                }

                reservation = reservationRepository.save(reservation);
            }
        }
        return reservation;
    }
/*
    @Override
    public Reservation cancelReservation(int id) {
        Optional<Reservation> optionalReservation = Optional.ofNullable(reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not exist with id :" + id)));
        if (optionalReservation.isPresent()) {
            Status reservationStatus= optionalReservation.getStatus();
            if(optionalReservation.equals(Status.RESERVED) || reservationStatus.equals(Status.CONFIRMED))
            {
                reservation.setStatus(Status.CANCELLED);
            }

        }
        return null;
    }
*/

}


