package com.crif.ticketbooking.exception;

public class NoSuchFlightExistsException extends RuntimeException{
	private String message;
	
	public NoSuchFlightExistsException() {}
	
	public NoSuchFlightExistsException(String msg) {
		super(msg);
		this.message=msg;
	}
}
