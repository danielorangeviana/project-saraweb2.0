package com.br.saraweb20.dto;

import java.io.Serializable;

import com.br.saraweb20.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String cpf;
	private String login;
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		cpf = obj.getCpf();
	}
}
