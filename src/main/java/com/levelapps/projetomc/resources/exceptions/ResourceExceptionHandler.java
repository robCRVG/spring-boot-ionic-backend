package com.levelapps.projetomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.levelapps.projetomc.services.exceptions.DataIntegrityException;
import com.levelapps.projetomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardErrors> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardErrors errors = new StandardErrors(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardErrors> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

		StandardErrors errors = new StandardErrors(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
