package com.crif.ticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crif.ticketbooking.exception.NoSuchPassengerExistsException;
import com.crif.ticketbooking.exception.PassengerAlreadyExistsException;
import com.crif.ticketbooking.exception.InvalidPassengerDataException;
import com.crif.ticketbooking.model.Passenger;
import com.crif.ticketbooking.repo.PassengerRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassenger(int passengerId) {
        return passengerRepository.findById(passengerId).orElse(null);
    }

    public Passenger addPassenger(Passenger passenger) {
        
    	Passenger existingPassenger = passengerRepository.findByPassengerPhoneNumber(passenger.getPassengerPhoneNumber());

        if (existingPassenger != null) {
            throw new PassengerAlreadyExistsException("A passenger with the same phone number already exists.");
        }
//    	if (passengerRepository.existsById(passenger.getPassengerId())) {
//            throw new PassengerAlreadyExistsException("Passenger already exists!!");
//        }

        //  age
        if (passenger.getPassengerAge() >= 99) {
            throw new InvalidPassengerDataException("Passenger age must be less than 99.");
        }

        // Dob
//        if (passenger.passengerDob().isAfter(LocalDate.now())) {
//        }

        LocalDate dob = passenger.getPassengerDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (dob.isAfter(LocalDate.now())) {
            throw new InvalidPassengerDataException("Passenger date of birth cannot be in the future.");
        }

        // Phone.no
        String phoneNumberPattern = "^\\d{10,12}$";
        if (!Pattern.matches(phoneNumberPattern, passenger.getPassengerPhoneNumber())) {
            throw new InvalidPassengerDataException("Passenger phone number must be between 10 to 12 digits.");
        }

        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Passenger updatedPassenger) {
        if (!passengerRepository.existsById(updatedPassenger.getPassengerId())) {
            throw new NoSuchPassengerExistsException("No such passenger exists with ID: " + updatedPassenger.getPassengerId());
        }

        // age
        if (updatedPassenger.getPassengerAge() >= 99) {
            throw new InvalidPassengerDataException("Passenger age must be less than 99.");
        }

        // dob
//        if (updatedPassenger.getPassengerDob().isAfter(LocalDate.now())) {
//        }
        
        LocalDate dob = updatedPassenger.getPassengerDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (dob.isAfter(LocalDate.now())) {
            throw new InvalidPassengerDataException("Passenger date of birth cannot be in the future.");
        }

        // phone no
        String phoneNumberPattern = "^\\d{10,12}$";
        if (!Pattern.matches(phoneNumberPattern, updatedPassenger.getPassengerPhoneNumber())) {
            throw new InvalidPassengerDataException("Passenger phone number must be between 10 to 12 digits.");
        }

        return passengerRepository.save(updatedPassenger);
    }

    public boolean deletePassenger(int passengerId) {
        if (passengerRepository.existsById(passengerId)) {
            passengerRepository.deleteById(passengerId);
            return true;
        }
        return false;
    }
}
