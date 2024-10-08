package com.crif.ticketbooking.controller;

import com.crif.ticketbooking.model.Ticket;
import com.crif.ticketbooking.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TicketControllerMockTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @Test
    public void createTicket_success() {
        Ticket mockTicket = new Ticket();
        mockTicket.setTicketId(1);
        mockTicket.setTicketNumber(12345);
        when(ticketService.createTicket(mockTicket)).thenReturn(mockTicket);
        ResponseEntity<Ticket> response = ticketController.createTicket(mockTicket);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTicket.getTicketNumber(), response.getBody().getTicketNumber());
    }

    @Test
    public void getTicketById_found() {
        int ticketId = 1;
        Ticket mockTicket = new Ticket();
        mockTicket.setTicketId(ticketId);
        mockTicket.setTicketNumber(12345);

        when(ticketService.getTicketById(ticketId)).thenReturn(Optional.of(mockTicket));

        ResponseEntity<Ticket> response = ticketController.getTicketById(ticketId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticketId, response.getBody().getTicketId());
    }

    @Test
    public void getTicketById_notFound() {
        int ticketId = 1;

        when(ticketService.getTicketById(ticketId)).thenReturn(Optional.empty());

        ResponseEntity<Ticket> response = ticketController.getTicketById(ticketId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAllTickets_success() {
        List<Ticket> mockTickets = new ArrayList<>();
        Ticket ticket1 = new Ticket();
        ticket1.setTicketId(1);
        ticket1.setTicketNumber(12345);

        Ticket ticket2 = new Ticket();
        ticket2.setTicketId(2);
        ticket2.setTicketNumber(67890);

        mockTickets.add(ticket1);
        mockTickets.add(ticket2);

        when(ticketService.getAllTickets()).thenReturn(mockTickets);

        ResponseEntity<List<Ticket>> response = ticketController.getAllTickets();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void updateTicket_success() {
        int ticketId = 1;
        Ticket existingTicket = new Ticket();
        existingTicket.setTicketId(ticketId);
        existingTicket.setTicketNumber(12345);

        Ticket updatedTicket = new Ticket();
        updatedTicket.setTicketId(ticketId);
        updatedTicket.setTicketNumber(54321);

        when(ticketService.updateTicket(ticketId, updatedTicket)).thenReturn(updatedTicket);

        ResponseEntity<Ticket> response = ticketController.updateTicket(ticketId, updatedTicket);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(54321, response.getBody().getTicketNumber());
    }

    @Test
    public void updateTicket_notFound() {
        int ticketId = 1;
        Ticket updatedTicket = new Ticket();
        updatedTicket.setTicketId(ticketId);
        updatedTicket.setTicketNumber(54321);

        when(ticketService.updateTicket(ticketId, updatedTicket)).thenReturn(null);

        ResponseEntity<Ticket> response = ticketController.updateTicket(ticketId, updatedTicket);

        
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteTicket_success() {
        int ticketId = 1;

        doNothing().when(ticketService).deleteTicket(ticketId);

        ResponseEntity<Void> response = ticketController.deleteTicket(ticketId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteTicket_notFound() {
        int ticketId = 1;

        doThrow(new RuntimeException("Ticket not found")).when(ticketService).deleteTicket(ticketId);

        ResponseEntity<Void> response = ticketController.deleteTicket(ticketId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
