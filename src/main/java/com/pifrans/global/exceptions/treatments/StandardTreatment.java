package com.pifrans.global.exceptions.treatments;

import java.io.Serializable;

/*
 * Classe modelo para trabalhar com tratação de erro padrão
 */
public class StandardTreatment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	private Long timeStamp;
	private String exception;

	public StandardTreatment(Integer status, String message, Long timeStamp, String exception) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
		this.exception = exception;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
