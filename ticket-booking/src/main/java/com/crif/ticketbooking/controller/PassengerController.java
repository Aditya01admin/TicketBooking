package com.crif.ticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crif.ticketbooking.model.Passenger;
import com.crif.ticketbooking.service.PassengerService;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable int passengerId) {
        Passenger passenger = passengerService.getPassenger(passengerId);
        return passenger != null ? ResponseEntity.ok(passenger) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
        Passenger savedPassenger = passengerService.addPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPassenger);
    }

    @PutMapping("/{passengerId}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable int passengerId, @RequestBody Passenger passenger) {
        passenger.setPassengerId(passengerId); // Set the ID for update
        Passenger updatedPassenger = passengerService.updatePassenger(passenger);
        return updatedPassenger != null ? ResponseEntity.ok(updatedPassenger) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable int passengerId) {
        boolean isDeleted = passengerService.deletePassenger(passengerId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
