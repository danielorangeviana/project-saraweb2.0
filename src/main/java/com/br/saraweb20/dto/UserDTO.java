package com.br.saraweb20.dto;

import java.util.HashSet;
import java.util.Set;

import com.br.saraweb20.entities.User;

public class UserDTO {
	
	private Long id;
	private String name;
	private String cpf;
	private String login;
	
	Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, String name, String cpf, String login) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.login = login;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		login = entity.getLogin();
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
}
