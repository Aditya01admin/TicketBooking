package com.crif.ticketbooking.exception;

public class FlightAlreadyExistsException extends RuntimeException {
	private String message;
	
	public FlightAlreadyExistsException() {}
	
	public FlightAlreadyExistsException(String msg) {
		super(msg);
		this.message=msg;
	}

}
