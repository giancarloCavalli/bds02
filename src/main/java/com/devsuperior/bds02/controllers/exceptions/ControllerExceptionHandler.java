package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> dataIntegrity(ConstraintViolationException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError(Instant.now(),
				status.value(), "Data Integrity Error", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(Instant.now(),
				status.value(), "Entity Not Found", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
}
