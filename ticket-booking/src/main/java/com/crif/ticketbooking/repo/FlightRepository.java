package com.crif.ticketbooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crif.ticketbooking.model.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long> {     // it will provide data access methods for Flight entities
    Flight findByFlightNo(String flightNo);                                 // custom query method to find a Flight by its flightNo
}
