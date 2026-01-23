package edu.aitu.oop.clinic.Exception;

public class TimeSlotAlreadyBookedException extends RuntimeException {
    public TimeSlotAlreadyBookedException(String message) {
        super(message);
    }
}
