package com.pifrans.global.exceptions.errors;

/*
 * Classe para tratar erros de integração de dados
 */
public class ErrorDataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErrorDataIntegrityException(String message) {
		super(message);
	}

	public ErrorDataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}

}
