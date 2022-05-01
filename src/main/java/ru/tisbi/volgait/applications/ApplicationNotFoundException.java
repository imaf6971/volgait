package ru.tisbi.volgait.applications;

public class ApplicationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6594382419414698941L;

	public ApplicationNotFoundException() {
	}

	public ApplicationNotFoundException(String message) {
		super(message);
	}

	public ApplicationNotFoundException(Throwable cause) {
		super(cause);
	}

	public ApplicationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
