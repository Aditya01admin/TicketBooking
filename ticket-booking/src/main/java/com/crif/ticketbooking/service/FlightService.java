package com.crif.ticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crif.ticketbooking.exception.FlightAlreadyExistsException;
import com.crif.ticketbooking.exception.NoSuchFlightExistsException;
import com.crif.ticketbooking.model.Flight;
import com.crif.ticketbooking.repo.FlightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;

	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	public Flight getFlight(String flightNo) {
		return flightRepository.findByFlightNo(flightNo);

	}

	public Flight addFlight(Flight flight) {
		Flight existingFlight = flightRepository.findById(flight.getId()).orElse(null);
		if (existingFlight == null) {
			return flightRepository.save(flight);
		} else {
			throw new FlightAlreadyExistsException("Flight already exists!!"); // or throw an exception
		}
	}

//	public Flight updateFlight(Flight updatedFlight) {
//		Optional<Flight> existingFlight = flightRepository.findById(updatedFlight.getId());
//		if (existingFlight.isPresent()) {
//			return flightRepository.save(updatedFlight);
//		} else {
//			return null; // or throw an exception
//		}
//	}
	
	public Flight updateFlight(Flight updatedFlight) {
        Optional<Flight> existingFlight = flightRepository.findById(updatedFlight.getId());
        if (!existingFlight.isEmpty()) {
            return flightRepository.save(updatedFlight);
        } else {
            throw new NoSuchFlightExistsException("No such flight exists with ID: " + updatedFlight.getId());
        }
    }

	public boolean deleteFlight(String flightNo) {
		Flight flight = flightRepository.findByFlightNo(flightNo);
		if (flight != null) {
			flightRepository.delete(flight);
			return true;
		} else {
			return false;
		}
	}
}
