
package com.crif.ticketbooking.exception;

public class NoSuchTicketExistsException extends RuntimeException {
    public NoSuchTicketExistsException(String message) {
        super(message);
    }
}
