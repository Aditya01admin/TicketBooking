package com.crif.ticketbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Passenger {

	@Id
	private int passengerId;

//	@Column(name = "FLIGHT_NO")	
//	private String flightNo;

	private String passengerName;
	private int passengerAge;
	private String passengerPhoneNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date passengerDob;


	@ManyToMany//(fetch = FetchType.LAZY)
	@JoinTable(
			name = "TICKET_PASSENGER",
			joinColumns = @JoinColumn(name = "ticket_id"),
			inverseJoinColumns = @JoinColumn(name = "passenger_id")
			
	)
	@JsonBackReference
	private List<Ticket> tickets = new ArrayList<>();

	// Getters and Setters

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getPassengerPhoneNumber() {
		return passengerPhoneNumber;
	}

	public void setPassengerPhoneNumber(String passengerPhoneNumber) {
		this.passengerPhoneNumber = passengerPhoneNumber;
	}

	public Date getPassengerDob() {
		return passengerDob;
	}

	public void setPassengerDob(Date passengerDob) {
		this.passengerDob = passengerDob;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
