package com.crif.ticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.service.FlightService;

import java.util.List;

@RestController
@RequestMapping("/flights")
@Component(value = "abc")
public class FlightController {

    @Autowired
    private FlightService flightService;

    public FlightController(FlightService flightService) {
    	this.flightService = flightService;
	}

	@GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{flightNo}")
    public ResponseEntity<Flight> getFlight(@PathVariable String flightNo) {
    	
        Flight flight = flightService.getFlight(flightNo);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
    	//if flight is Null 
    	//if flight no is null or empty
    	//
        Flight savedFlight = flightService.addFlight(flight);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }

    @PutMapping("/{flightNo}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String flightNo, @RequestBody Flight flight) {
    	
    	//CHeck, flight No not empty
    	//Flight no in path match with flight.flightNnUMBER
    	
        
        
        Flight updatedFlight = flightService.updateFlight(flight);
        if (updatedFlight != null) {
            return ResponseEntity.ok(updatedFlight);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{flightNo}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String flightNo) {
        boolean isDeleted = flightService.deleteFlight(flightNo);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
