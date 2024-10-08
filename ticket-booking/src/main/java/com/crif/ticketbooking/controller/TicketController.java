package com.crif.ticketbooking.controller;

import com.crif.ticketbooking.model.Ticket;
import com.crif.ticketbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    
    

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);
        return ResponseEntity.ok(createdTicket);
    }

    // Get a ticket by ID
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int ticketId) {
        Optional<Ticket> ticketOpt = ticketService.getTicketById(ticketId);
        if (ticketOpt.isPresent()) {
            return ResponseEntity.ok(ticketOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable int ticketId, @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketService.updateTicket(ticketId, ticket);
        if (updatedTicket != null) {
            return ResponseEntity.ok(updatedTicket);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
    
}
