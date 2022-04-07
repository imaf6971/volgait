package ru.tisbi.volgait.registration.exception;

public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException() {
    }

    public EmailAlreadyTakenException(String arg0) {
        super(arg0);
    }

    public EmailAlreadyTakenException(Throwable arg0) {
        super(arg0);
    }

    public EmailAlreadyTakenException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public EmailAlreadyTakenException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 instanceof EmailAlreadyTakenException) {
            return true;
        }
        return false;
    }
    
}
