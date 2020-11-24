package com.pifrans.global.exceptions.errors;

/*
 * Classe para tratar erros de objetos nulos
 */
public class ErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErrorException(String message) {
		super(message);
	}

	public ErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
