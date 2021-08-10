package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.PassengerRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Passenger;
import com.edu.cs554.airlinesreservationsystem.model.Role;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.repository.PassengerRepository;
import com.edu.cs554.airlinesreservationsystem.service.PassengerService;
import com.edu.cs554.airlinesreservationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserService userService;

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
    public Passenger create(PassengerRegistrationRequest request) {

        User user = new User(request.getUser().getUserName(), request.getUser().getPassword(), Role.PASSENGER);

        user = userService.encodePassword(user);

        Passenger passenger = new Passenger(request.getFirstName(), request.getLastName(), request.getDateOfBirth(), request.getResidenceAddress(), user, request.getEmail());

        return passengerRepository.save(passenger);

    }

}
