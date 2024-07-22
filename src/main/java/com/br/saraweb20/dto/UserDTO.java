package com.br.saraweb20.dto;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

import com.br.saraweb20.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserDTO {
	
	private Long id;
	
	@NotBlank(message = "Entry the full name")
	private String name;
	
	@CPF(message = "Entry valid CPF")
	private String cpf;
	
	@NotBlank(message = "Entry valid email")
	private String email;
	
	@NotEmpty(message = "Must have at least one role")
	Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name, String cpf, String email) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		email = entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
}
