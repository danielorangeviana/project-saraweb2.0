package com.br.saraweb20.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.saraweb20.entities.Term;
import com.br.saraweb20.repositories.TermRepository;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

@Service
public class TermService {
	
	@Autowired
	private TermRepository repository;
	
	public List<Term> findAll() {
		return repository.findAll();
	}
	
	public Term findById(Long id) {
		Optional<Term> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
