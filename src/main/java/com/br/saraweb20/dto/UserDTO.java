package com.br.saraweb20.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import com.br.saraweb20.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class UserDTO {
	
	private Long id;
	
	@NotBlank(message = "Entry the full name")
	private String name;
	
	@CPF(message = "Entry valid CPF")
	private String cpf;
	
	@NotBlank(message = "Entry valid email")
	@Email(message = "Entry valid email")
	private String email;
	
	@NotNull(message = "Must have at least one role")
	Set<RoleDTO> roles = new HashSet<>();

}
