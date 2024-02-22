package com.Trip.exceptions;

public class TripNotAvailableException extends RuntimeException {
    public TripNotAvailableException(String message) {
        super(message);
    }
}
