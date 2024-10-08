package com.crif.ticketbooking.service;

import com.crif.ticketbooking.exception.NoSuchFlightExistsException;
import com.crif.ticketbooking.exception.NoSuchPassengerExistsException;
import com.crif.ticketbooking.exception.NoSuchTicketExistsException;
import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.model.Passenger;
import com.crif.ticketbooking.model.Ticket;
import com.crif.ticketbooking.repo.FlightRepository;
import com.crif.ticketbooking.repo.PassengerRepository;
import com.crif.ticketbooking.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private PassengerRepository passengerRepository;

    public Ticket createTicket(Ticket ticket) {
        Flight flight = flightRepository.findByFlightNo(ticket.getFlight().getFlightNo());
        if (flight == null) {
            throw new NoSuchFlightExistsException("Flight with number " + ticket.getFlight().getFlightNo() + " not found.");
        }
        
        ticket.setFlight(flight);
        
        List<Passenger> fetchedPassengers = new ArrayList<>();
        if (ticket.getPassengers() != null && !ticket.getPassengers().isEmpty()) {
            for (Passenger p : ticket.getPassengers()) {
                Optional<Passenger> fetchedPassenger = passengerRepository.findById(p.getPassengerId());
                if (fetchedPassenger.isPresent()) {
                    fetchedPassengers.add(fetchedPassenger.get());
                } else {
                    throw new NoSuchPassengerExistsException("Passenger with ID " + p.getPassengerId() + " not found.");
                }                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
            }
        }
        
        ticket.setPassengers(fetchedPassengers);

        return ticketRepository.save(ticket);
    }

    
    public Optional<Ticket> getTicketById(int ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isEmpty()) {
            throw new NoSuchTicketExistsException("Ticket with ID " + ticketId + " not found.");
        }
                
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(int ticketId, Ticket updatedTicket) {
        Optional<Ticket> existingTicketOpt = ticketRepository.findById(ticketId);
        if (existingTicketOpt.isPresent()) {
            Ticket existingTicket = existingTicketOpt.get();
            existingTicket.setTicketNumber(updatedTicket.getTicketNumber());
            existingTicket.setFlight(updatedTicket.getFlight());
            return ticketRepository.save(existingTicket);
        } else {
            throw new NoSuchTicketExistsException("Ticket with ID " + ticketId + " not found.");
        }
    }

    public void deleteTicket(int ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new NoSuchTicketExistsException("Ticket with ID " + ticketId + " not found.");
        }
        ticketRepository.deleteById(ticketId);
    }
}
