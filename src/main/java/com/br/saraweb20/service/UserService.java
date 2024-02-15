package com.br.saraweb20.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.saraweb20.entities.User;
import com.br.saraweb20.repositories.UserRepository;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<User> findByLogin(String login) {
		return repository.findByLogin(login);
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
}
