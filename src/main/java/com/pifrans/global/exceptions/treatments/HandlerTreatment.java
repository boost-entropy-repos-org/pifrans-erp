package com.pifrans.global.exceptions.treatments;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.pifrans.global.exceptions.errors.ErrorDataIntegrityException;
import com.pifrans.global.exceptions.errors.ErrorException;
import com.pifrans.global.exceptions.errors.ErrorNoSuchElementException;

import javassist.tools.rmi.ObjectNotFoundException;

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
	public ResponseEntity<StandardTreatment> objectNotFound(ObjectNotFoundException exception,
			HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis(), ObjectNotFoundException.class.getName());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ErrorException.class)
	public ResponseEntity<StandardTreatment> standard(ErrorException exception,
			HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis(), ErrorException.class.getName());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardTreatment> nullPointer(NullPointerException exception,
			HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis(), NullPointerException.class.getName());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(ErrorNoSuchElementException.class)
	public ResponseEntity<StandardTreatment> noSuchElement(ErrorNoSuchElementException exception,
			HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis(), ErrorNoSuchElementException.class.getName());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	/**
	 * Classe para tratar erros de {@link ErrorDataIntegrityException}
	 */
	@ExceptionHandler(ErrorDataIntegrityException.class)
	public ResponseEntity<StandardTreatment> dataIntegrity(ErrorDataIntegrityException exception,
			HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
				System.currentTimeMillis(), ErrorDataIntegrityException.class.getName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<StandardTreatment> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpServletRequest request) {
		StandardTreatment error = new StandardTreatment(HttpStatus.NOT_FOUND.value(),
				"Path inválido: " + request.getServletPath(), System.currentTimeMillis(), ex.getClass().getName());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}