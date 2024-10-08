package com.crif.ticketbooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crif.ticketbooking.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger findByPassengerPhoneNumber(String phoneNumber);
    }
