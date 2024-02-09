package com.br.saraweb20.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.saraweb20.entities.User;
import com.br.saraweb20.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "orangedwcov", "123456");
		User u2 = new User(null, "dryelewdar", "654321");
		User u3 = new User(null, "deivsondcc", "1234");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
	}
}
