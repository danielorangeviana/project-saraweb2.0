package com.br.saraweb20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.saraweb20.dto.EmailDTO;
import com.br.saraweb20.dto.newPasswordDTO;
import com.br.saraweb20.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/recover-token")
	public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO body) {
		authService.createRecoverToken(body);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/new-password")
	public ResponseEntity<Void> createNewPassword(@Valid @RequestBody newPasswordDTO newPassword) {
		authService.saveNewPassword(newPassword);
		return ResponseEntity.noContent().build();
	}

}
