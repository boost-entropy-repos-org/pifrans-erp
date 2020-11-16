package com.pifrans.global.exceptions.errors;

public class NoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSuchElementException(String message) {
		super(message);
	}

	public NoSuchElementException(String message, Throwable cause) {
		super(message, cause);
	}
}
