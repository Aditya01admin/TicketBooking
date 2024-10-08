package com.crif.ticketbooking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crif.ticketbooking.model.Passenger;
import com.crif.ticketbooking.service.PassengerService;

@SpringBootTest
public class PassengerControllerMockTest {

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private PassengerController passengerController;

    @Test
    public void getAllPassengers_success() {
        // Mocking the passengerService to return a list of passengers
        List<Passenger> mockPassengers = new ArrayList<>();
        Passenger passenger1 = new Passenger();
        passenger1.setPassengerId(1);
        passenger1.setPassengerName("John Doe");

        Passenger passenger2 = new Passenger();
        passenger2.setPassengerId(2);
        passenger2.setPassengerName("Jane Smith");

        mockPassengers.add(passenger1);
        mockPassengers.add(passenger2);

        when(passengerService.getAllPassengers()).thenReturn(mockPassengers);

        // Call the controller method
        ResponseEntity<List<Passenger>> response = passengerController.getAllPassengers();

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void getPassenger_found() {
        int passengerId = 1;
        Passenger mockPassenger = new Passenger();
        mockPassenger.setPassengerId(passengerId);
        mockPassenger.setPassengerName("John Doe");

        when(passengerService.getPassenger(passengerId)).thenReturn(mockPassenger);

        ResponseEntity<Passenger> response = passengerController.getPassenger(passengerId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passengerId, response.getBody().getPassengerId());
    }

    @Test
    public void getPassenger_notFound() {
        int passengerId = 1;

        when(passengerService.getPassenger(passengerId)).thenReturn(null);

        ResponseEntity<Passenger> response = passengerController.getPassenger(passengerId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void addPassenger_success() {
        Passenger newPassenger = new Passenger();
        newPassenger.setPassengerId(1);
        newPassenger.setPassengerName("John Doe");

        when(passengerService.addPassenger(newPassenger)).thenReturn(newPassenger);

        ResponseEntity<Passenger> response = passengerController.addPassenger(newPassenger);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getPassengerName());
    }

    @Test
    public void updatePassenger_success() {
        int passengerId = 1;
        Passenger existingPassenger = new Passenger();
        existingPassenger.setPassengerId(passengerId);
        existingPassenger.setPassengerName("John Doe");

        Passenger updatedPassenger = new Passenger();
        updatedPassenger.setPassengerId(passengerId);
        updatedPassenger.setPassengerName("Jane Doe");

        when(passengerService.updatePassenger(updatedPassenger)).thenReturn(updatedPassenger);

        ResponseEntity<Passenger> response = passengerController.updatePassenger(passengerId, updatedPassenger);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane Doe", response.getBody().getPassengerName());
    }

    @Test
    public void updatePassenger_notFound() {
        int passengerId = 1;
        Passenger updatedPassenger = new Passenger();
        updatedPassenger.setPassengerId(passengerId);
        updatedPassenger.setPassengerName("Jane Doe");

        when(passengerService.updatePassenger(updatedPassenger)).thenReturn(null);

        ResponseEntity<Passenger> response = passengerController.updatePassenger(passengerId, updatedPassenger);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deletePassenger_success() {
        int passengerId = 1;

        // Mocking the passengerService to return true (passenger deleted)
        when(passengerService.deletePassenger(passengerId)).thenReturn(true);

        // Call the controller method
        ResponseEntity<Void> response = passengerController.deletePassenger(passengerId);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deletePassenger_notFound() {
        int passengerId = 1;

        when(passengerService.deletePassenger(passengerId)).thenReturn(false);

        ResponseEntity<Void> response = passengerController.deletePassenger(passengerId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
