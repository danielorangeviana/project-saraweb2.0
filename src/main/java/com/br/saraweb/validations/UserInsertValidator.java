package com.br.saraweb.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.saraweb.exceptions.FieldMessage;
import com.br.saraweb.dto.UserInsertDTO;
import com.br.saraweb.entities.User;
import com.br.saraweb.repositories.UserRepository;
import com.br.saraweb.validations.constraints.UserInsertValid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
	
	@Autowired
	UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		User user = repository.findByEmail(dto.getEmail());
		if (user != null) {
			list.add(new FieldMessage("email", "Email already exists"));
		}
		
		user = repository.findByCpf(dto.getCpf());
		if (user != null) {
			list.add(new FieldMessage("cpf", "CPF already exists"));
		}

		for (FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
