package com.crif.ticketbooking.exception;

public class PassengerAlreadyExistsException extends RuntimeException {
	private String message;
	
	public PassengerAlreadyExistsException() {}
	
	public PassengerAlreadyExistsException(String msg) {
		super(msg);
		this.message=msg;
	}

}
