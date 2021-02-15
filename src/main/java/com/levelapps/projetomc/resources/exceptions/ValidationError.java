package com.levelapps.projetomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardErrors {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fiedName, String message) {
		this.errors.add(new FieldMessage(fiedName, message));
	}
	
	

}
