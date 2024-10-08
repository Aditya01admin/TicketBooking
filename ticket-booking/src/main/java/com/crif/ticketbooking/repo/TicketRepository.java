package com.crif.ticketbooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crif.ticketbooking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
