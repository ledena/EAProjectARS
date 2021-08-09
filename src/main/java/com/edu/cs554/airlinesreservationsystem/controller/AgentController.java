package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.dto.AgentPatchRequest;
import com.edu.cs554.airlinesreservationsystem.dto.AgentRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.AgentUpdateRequest;
import com.edu.cs554.airlinesreservationsystem.model.Agent;
import com.edu.cs554.airlinesreservationsystem.model.Person;
import com.edu.cs554.airlinesreservationsystem.service.AgentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/agent")
@Transactional()
public class AgentController {

    @Autowired
    private AgentService agentService;

    // Registers or Adds agent to DB
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createAgent(@RequestBody AgentRegistrationRequest request) {
        return agentService.create(request);
    }

    @GetMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Agent> getAllAgents() {
        return agentService.findAll();
    }

    @GetMapping(value = { "/{agentId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAgent(@PathVariable long agentId) throws JSONException {

        JSONObject responseBody = new JSONObject();

        Optional<Agent> optionalAgent = agentService.findById(agentId);

        if (optionalAgent.isPresent()) {
            return new ResponseEntity<>(optionalAgent.get(), HttpStatus.OK);
        } else {
            responseBody.put("success", false);
            responseBody.put("message", "Agent not found");
            return new ResponseEntity<>(responseBody.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = { "/{agentId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAgent(@PathVariable long agentId, @RequestBody AgentUpdateRequest agentUpdateRequest) throws JSONException {

        Optional<Agent> optionalAgent = agentService.findById(agentId);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (optionalAgent.isPresent()) {

            Agent agent = optionalAgent.get();

            agent.setFirstName(agentUpdateRequest.getFirstName());
            agent.setLastName(agentUpdateRequest.getLastName());
            agent.setResidenceAddress(agentUpdateRequest.getResidenceAddress());
            agent = agentService.update(agent);

            responseBody.put("success", true);
            responseBody.put("message", "Agent successfully updated");
            responseBody.put("agent", agent);

            return new ResponseEntity<>(agent, httpStatus);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Agent not found");
            responseBody.put("agentId", agentId);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseBody.toString(), httpStatus);

        }
    }

    @PatchMapping(value = { "/{agentId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchAgent(@PathVariable long agentId, @RequestBody AgentPatchRequest agentPatchRequest) throws JSONException {

        Optional<Agent> optionalAgent = agentService.findById(agentId);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (optionalAgent.isPresent()) {

            Agent agent = optionalAgent.get();

            agent.setFirstName(agentPatchRequest.getFirstName());
            agent.setLastName(agentPatchRequest.getLastName());
            agent = agentService.update(agent);

            responseBody.put("success", true);
            responseBody.put("message", "Agent successfully updated");
            responseBody.put("agent", agent);

            return new ResponseEntity<>(agent, httpStatus);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Agent not found");
            responseBody.put("agentId", agentId);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseBody.toString(), httpStatus);

        }
    }

    @DeleteMapping(value = { "/{agentId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAgent(@PathVariable long agentId) throws JSONException {

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (agentService.deleteAgentById(agentId)) {

            responseBody.put("success", true);
            responseBody.put("message", "Successfully deleted");
            responseBody.put("agentId", agentId);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Unable to delete: Error");
            responseBody.put("agentId", agentId);
            httpStatus = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity<>(responseBody.toString(), httpStatus);
    }

}
