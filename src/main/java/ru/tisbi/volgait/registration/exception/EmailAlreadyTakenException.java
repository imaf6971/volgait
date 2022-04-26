package ru.tisbi.volgait.registration.exception;

public class EmailAlreadyTakenException extends RegistrationException {

    public EmailAlreadyTakenException() {
        super();
    }

    public EmailAlreadyTakenException(String arg0) {
        super(arg0);
    }

    @Override
    public boolean equals(Object arg0) {
        return arg0 instanceof EmailAlreadyTakenException;
    }
    
}
