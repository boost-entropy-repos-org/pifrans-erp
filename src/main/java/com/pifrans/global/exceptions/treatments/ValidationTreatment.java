package com.pifrans.global.exceptions.treatments;

import java.util.ArrayList;
import java.util.List;

public class ValidationTreatment extends StandardTreatment {
	private static final long serialVersionUID = 1L;
	private List<FieldMessageTreatment> errors = new ArrayList<>();

	public ValidationTreatment(Integer status, String msg, Long timeStamp, String exception) {
		super(status, msg, timeStamp, exception);
	}

	public List<FieldMessageTreatment> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessageTreatment(fieldName, message));
	}

}
