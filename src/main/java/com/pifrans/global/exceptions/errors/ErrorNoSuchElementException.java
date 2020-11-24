package com.pifrans.global.exceptions.errors;

/*
 * Classe para tratar erros de objetos nulos
 */
public class ErrorNoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErrorNoSuchElementException(String message) {
		super(message);
	}

	public ErrorNoSuchElementException(String message, Throwable cause) {
		super(message, cause);
	}
}
