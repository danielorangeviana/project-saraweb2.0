package com.br.saraweb20.service.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.br.saraweb20.service.validation.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface PasswordValid {
	String message() default "The password must contain: "
			+ "8 characters minimum; "
			+ "1 capital letter at least; "
			+ "1 number at least; "
			+ "1 Symbol at least: $*&@#";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
