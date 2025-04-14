package com.br.saraweb20.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class newPasswordDTO {
	
	@NotBlank(message = "token is required")
	private String token;
	
	@NotBlank(message = "New password is required")
	@Size(min = 6, message = "Must have at least 6 characters")
	private String password;
}
