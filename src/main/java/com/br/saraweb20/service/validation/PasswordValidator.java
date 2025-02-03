package com.br.saraweb20.service.validation;

import com.br.saraweb20.service.validation.constraints.PasswordValid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
	
	@Override
	public void initialize(PasswordValid constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		String password = value == null ? "" : value;

		return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$");
	}
}
