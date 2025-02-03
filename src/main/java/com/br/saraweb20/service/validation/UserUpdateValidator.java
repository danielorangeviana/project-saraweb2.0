package com.br.saraweb20.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.br.saraweb20.controller.exceptions.FieldMessage;
import com.br.saraweb20.dto.UserUpdateDTO;
import com.br.saraweb20.entities.User;
import com.br.saraweb20.repositories.UserRepository;
import com.br.saraweb20.service.validation.constraints.UserUpdateValid;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
				
		User user = repository.findByEmail(dto.getEmail());
		if (user != null && !user.getId().equals(userId)) {
			list.add(new FieldMessage("email", "Email already exists"));
		}
		
		user = repository.findByCpf(dto.getCpf());
		if (user != null && !user.getId().equals(userId)) {
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
