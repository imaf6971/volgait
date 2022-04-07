package ru.tisbi.volgait.registration.exception;

public class PasswordsDoesntMatchException extends RuntimeException {

    public PasswordsDoesntMatchException() {
    }

    public PasswordsDoesntMatchException(String arg0) {
        super(arg0);
    }

    @Override
    public boolean equals(Object arg0) {
        return arg0 instanceof PasswordsDoesntMatchException;
    }
}
