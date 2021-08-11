package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.AgentRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.PassengerReservationResponseDto;
import com.edu.cs554.airlinesreservationsystem.model.Agent;
import com.edu.cs554.airlinesreservationsystem.model.Reservation;
import com.edu.cs554.airlinesreservationsystem.model.Role;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.repository.AgentRepository;
import com.edu.cs554.airlinesreservationsystem.repository.ReservationRepository;
import com.edu.cs554.airlinesreservationsystem.service.AgentService;
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
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    public Optional<Agent> findById(long agentId) {
        return agentRepository.findById(agentId);
    }

    public Agent update(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public boolean deleteAgentById(long agentId) {
        if(agentRepository.existsById(agentId)) {
            agentRepository.deleteById(agentId);
            return !agentRepository.existsById(agentId);
        } else
            return false;
    }

    @Override
    public List<PassengerReservationResponseDto> findReservationByAgent(User user) {
        List<Reservation> reservations = reservationRepository.findAllByReservedBy(user);
        List<PassengerReservationResponseDto> pDto= new ArrayList<>();
        if(reservations.size()!=0){
            pDto= listMapper.mapList(reservations, PassengerReservationResponseDto.class);
            return pDto;
        }
        return null;
    }

    @Override
    public PassengerReservationResponseDto findAgentReservationById(User user, int id) {
        Reservation reservation = reservationRepository.findAllByReservedByAndId(user,id);
        return modelMapper.map(reservation,PassengerReservationResponseDto.class);
    }

    @Override
    public Agent create(AgentRegistrationRequest request) {

        User user = new User(request.getUser().getUserName(), request.getUser().getPassword(), Role.AGENT);

        user = userService.encodePassword(user);

        Agent agent = new Agent(request.getFirstName(), request.getLastName(), request.getResidenceAddress(), user);

        return agentRepository.save(agent);

    }

}
