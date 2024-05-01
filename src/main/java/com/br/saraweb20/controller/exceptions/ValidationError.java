package com.br.saraweb20.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<>();
	
	public void addErrors(String fieldMessage, String message) {
		errors.add(new FieldMessage(fieldMessage, message));
	}
}
