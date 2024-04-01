package com.br.saraweb20.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.repositories.CelebrantRepository;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

@Service
public class CelebrantService {
	
	@Autowired
	private CelebrantRepository repository;
	
	public List<Celebrant> findAll() {
		return repository.findAll();
	}
	
	public Celebrant findById(Long id) {
		Optional<Celebrant> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Celebrant> findByName(String name) {
		return repository.findByName(name);
	}
	
	public Celebrant insert(Celebrant obj) {
		return repository.save(obj);
	}
}
