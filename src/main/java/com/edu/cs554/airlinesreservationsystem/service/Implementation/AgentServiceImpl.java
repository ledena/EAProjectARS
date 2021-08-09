package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.AgentRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Agent;
import com.edu.cs554.airlinesreservationsystem.model.Role;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.repository.AgentRepository;
import com.edu.cs554.airlinesreservationsystem.service.AgentService;
import com.edu.cs554.airlinesreservationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserService userService;

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
    public Agent create(AgentRegistrationRequest request) {

        User user = new User(request.getUser().getUserName(), request.getUser().getPassword(), Role.AGENT);

        user = userService.encodePassword(user);

        Agent agent = new Agent(request.getFirstName(), request.getLastName(), request.getResidenceAddress(), user);

        return agentRepository.save(agent);

    }

}
