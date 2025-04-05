package main.java.exceptions;

public class ProgramaMeException extends Exception {

	public ProgramaMeException() {
	}

	public ProgramaMeException(String message) {
		super(message);
	}

	public ProgramaMeException(Throwable cause) {
		super(cause);
	}

	public ProgramaMeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProgramaMeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
