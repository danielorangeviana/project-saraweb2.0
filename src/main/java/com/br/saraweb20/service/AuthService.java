package com.br.saraweb20.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.EmailDTO;
import com.br.saraweb20.dto.newPasswordDTO;
import com.br.saraweb20.entities.PasswordRecover;
import com.br.saraweb20.entities.User;
import com.br.saraweb20.repositories.PasswordRecoverRepository;
import com.br.saraweb20.repositories.UserRepository;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

@Service
public class AuthService {
	
	@Value("${email.password-recover.token.minutes}")
	private Long tokenMinutes;
	
	@Value("${email.password-recover.uri}")
	private String recoverUri;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordRecoverRepository passwordRecoverRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	@Transactional
	public void createRecoverToken(EmailDTO body) {
		User user = userRepository.findByEmail(body.getEmail());
		if(user == null) throw new ResourceNotFoundException("Email not found");
		
		String token = UUID.randomUUID().toString();
		
		PasswordRecover entity = new PasswordRecover();
		entity.setEmail(body.getEmail());
		entity.setToken(token);
		entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60));
		passwordRecoverRepository.save(entity);
		
		String bodyRecover = "Follow the link to set a new password \n\n"
				+ recoverUri + token;
		
		emailService.sendEmail(body.getEmail(), "Password recovery", bodyRecover);
		
	}


	public void saveNewPassword(newPasswordDTO body) {
		
		List<PasswordRecover> validToken = passwordRecoverRepository.searchValidToken(body.getToken(), Instant.now());
		
		if(validToken == null || validToken.isEmpty()) throw new ResourceNotFoundException("Token invalid");
		
		User user = userRepository.findByEmail(validToken.get(0).getEmail());
		if(user == null) throw new ResourceNotFoundException("User not found for email associated with token");
		
		user.setPassword(passwordEncoder.encode(body.getPassword()));
		user = userRepository.save(user);
	}

}
