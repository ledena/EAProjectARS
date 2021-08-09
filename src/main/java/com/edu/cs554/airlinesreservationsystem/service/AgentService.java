package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.AgentRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Agent;

import java.util.List;
import java.util.Optional;

public interface AgentService {
    Agent create(AgentRegistrationRequest request);
    List<Agent> findAll();
    Optional<Agent> findById(long agentId);
    Agent update(Agent agent);
    boolean deleteAgentById(long agentId);

}
