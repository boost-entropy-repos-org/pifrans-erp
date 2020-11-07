package com.pifrans.exceptions.treatments;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pifrans.exceptions.errors.DataIntegrityException;
import com.pifrans.exceptions.errors.ObjectNotFoundException;

/*
 * Classe para tratar erros gerenciados por classes do pacote exceptions.errors
 */
@ControllerAdvice
public class HandlerTreatment implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Trata erro do tipo {@link ObjectNotFoundException}
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardTreatment> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	/**
	 * Classe para tratar erros de {@link DataIntegrityException}
	 */
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardTreatment> dataIntegrity(DataIntegrityException exception, HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
