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

import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.service.FlightService;

@SpringBootTest
public class FlightControllerMockTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    @Test
    public void getAllFlights_list_null() {
        // Mock flightService to return null
        when(flightService.getAllFlights()).thenReturn(null);

        // Call the controller method
        ResponseEntity<List<Flight>> responseObject = flightController.getAllFlights();

        // Assertions
        assertNotNull(responseObject);
        assertEquals(HttpStatus.OK, responseObject.getStatusCode());
        assertEquals(null, responseObject.getBody());
    }

    @Test
    public void getAllFlights_list_empty() {
        when(flightService.getAllFlights()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Flight>> responseObject = flightController.getAllFlights();

        assertNotNull(responseObject);
        assertEquals(HttpStatus.OK, responseObject.getStatusCode());
        assertNotNull(responseObject.getBody());
        assertEquals(0, responseObject.getBody().size());
    }

    @Test
    public void getFlight_flight_found() {
        String flightNo = "FL123";
        Flight mockFlight = new Flight();
        mockFlight.setFlightNo(flightNo);
        mockFlight.setToStation("New York");

        when(flightService.getFlight(flightNo)).thenReturn(mockFlight);

        ResponseEntity<Flight> responseObject = flightController.getFlight(flightNo);

        assertNotNull(responseObject);
        assertEquals(HttpStatus.OK, responseObject.getStatusCode());
        assertNotNull(responseObject.getBody());
        assertEquals(flightNo, responseObject.getBody().getFlightNo());
    }

    @Test
    public void getFlight_flight_not_found() {
        String flightNo = "FL123";

        when(flightService.getFlight(flightNo)).thenReturn(null);

        ResponseEntity<Flight> responseObject = flightController.getFlight(flightNo);

        assertNotNull(responseObject);
        assertEquals(HttpStatus.NOT_FOUND, responseObject.getStatusCode());
    }

    @Test
    public void addFlight_success() {
        Flight newFlight = new Flight();
        newFlight.setFlightNo("FL123");
        newFlight.setToStation("London");

        when(flightService.addFlight(newFlight)).thenReturn(newFlight);

        ResponseEntity<Flight> responseObject = flightController.addFlight(newFlight);

        assertNotNull(responseObject);
        assertEquals(HttpStatus.CREATED, responseObject.getStatusCode());
        assertNotNull(responseObject.getBody());
        assertEquals("FL123", responseObject.getBody().getFlightNo());
    }

  

    @Test
    public void deleteFlight_flight_found() {
        String flightNo = "FL123";

        when(flightService.deleteFlight(flightNo)).thenReturn(true);

        ResponseEntity<Void> responseObject = flightController.deleteFlight(flightNo);

        assertNotNull(responseObject);
        assertEquals(HttpStatus.NO_CONTENT, responseObject.getStatusCode());
    }

    @Test
    public void deleteFlight_flight_not_found() {
        String flightNo = "FL123";

        when(flightService.deleteFlight(flightNo)).thenReturn(false);

        ResponseEntity<Void> responseObject = flightController.deleteFlight(flightNo);

        assertNotNull(responseObject);
        assertEquals(HttpStatus.NOT_FOUND, responseObject.getStatusCode());
    }
}
