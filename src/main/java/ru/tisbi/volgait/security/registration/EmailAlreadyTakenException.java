package ru.tisbi.volgait.security.registration;

public class EmailAlreadyTakenException extends RuntimeException {

	private static final long serialVersionUID = -2580442846828163322L;

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
