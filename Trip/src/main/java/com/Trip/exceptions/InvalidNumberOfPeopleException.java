package com.Trip.exceptions;

public class InvalidNumberOfPeopleException extends RuntimeException {
    public InvalidNumberOfPeopleException(String message) {
        super(message);
    }
}
