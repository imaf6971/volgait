package ru.tisbi.volgait.registration.exception;

public class PasswordsDoesntMatchException extends RuntimeException {

    public PasswordsDoesntMatchException() {
    }

    public PasswordsDoesntMatchException(String arg0) {
        super(arg0);
    }

    public PasswordsDoesntMatchException(Throwable arg0) {
        super(arg0);
    }

    public PasswordsDoesntMatchException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public PasswordsDoesntMatchException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 instanceof PasswordsDoesntMatchException) {
            return true;
        }
        return false;
    }

    
}
