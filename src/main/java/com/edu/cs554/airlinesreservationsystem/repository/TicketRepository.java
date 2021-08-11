package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.model.Airport;
import com.edu.cs554.airlinesreservationsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
