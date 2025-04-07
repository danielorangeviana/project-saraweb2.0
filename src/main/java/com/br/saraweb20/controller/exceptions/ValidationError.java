package com.br.saraweb20.controller.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
	
	public List<FieldMessage> getErrors() {
        return errors;
    }
	
	public void addErrors(String fieldMessage, String message) {
		errors.removeIf(x -> x.getFieldName().equals(fieldMessage));
		errors.add(new FieldMessage(fieldMessage, message));
	}

}
