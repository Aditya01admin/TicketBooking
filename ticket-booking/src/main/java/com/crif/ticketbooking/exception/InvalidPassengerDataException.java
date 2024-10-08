package com.crif.ticketbooking.exception;

public class InvalidPassengerDataException extends RuntimeException {
    public InvalidPassengerDataException(String message) {
        super(message);
    }

    public InvalidPassengerDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
