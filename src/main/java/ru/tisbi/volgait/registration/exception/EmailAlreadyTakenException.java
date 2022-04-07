package ru.tisbi.volgait.registration.exception;

public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException() {
    }

    public EmailAlreadyTakenException(String arg0) {
        super(arg0);
    }

    @Override
    public boolean equals(Object arg0) {
        return arg0 instanceof EmailAlreadyTakenException;
    }
    
}
