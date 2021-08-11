package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.PassengerRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.PassengerReservationResponseDto;
import com.edu.cs554.airlinesreservationsystem.model.Passenger;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import com.edu.cs554.airlinesreservationsystem.model.Role;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.repository.PassengerRepository;
import com.edu.cs554.airlinesreservationsystem.repository.ReservationRepository;
import com.edu.cs554.airlinesreservationsystem.service.PassengerService;
import com.edu.cs554.airlinesreservationsystem.service.UserService;
import com.edu.cs554.airlinesreservationsystem.util.ListMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> findById(long passengerId) {
        return passengerRepository.findById(passengerId);
    }

    public Passenger update(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public boolean deletePassengerById(long passengerId) {
        if(passengerRepository.existsById(passengerId)) {
            passengerRepository.deleteById(passengerId);
            return !passengerRepository.existsById(passengerId);
        } else
            return false;
    }

    @Override
    public PassengerReservationResponseDto findPassengerReservationById(Passenger passenger, int id) {
        Reservation reservation= reservationRepository.findAllByPassengerAndId(passenger, id);
        if(reservation!=null){
            return modelMapper.map(reservation, PassengerReservationResponseDto.class);
        }
        return null;
    }

    @Override
    public List<PassengerReservationResponseDto> findPassengerReservations(Passenger passenger) {
//        return reservationRepository.findAllByPassenger(passenger);
        List<Reservation> reservations= reservationRepository.findAllByPassenger(passenger);
        List<PassengerReservationResponseDto> pDto= new ArrayList<>();
        if(reservations.size()!=0){
            pDto= listMapper.mapList(reservations, PassengerReservationResponseDto.class);
            return pDto;
        }
        return null;
    }

    @Override
    public Passenger create(PassengerRegistrationRequest request) {

        User user = new User(request.getUser().getUserName(), request.getUser().getPassword(), Role.PASSENGER);

        user = userService.encodePassword(user);

        Passenger passenger = new Passenger(request.getFirstName(), request.getLastName(), request.getDateOfBirth(), request.getResidenceAddress(), user, request.getEmail());

        return passengerRepository.save(passenger);

    }

}
