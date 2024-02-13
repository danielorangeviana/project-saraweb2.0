package com.br.saraweb20.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.repositories.BaptismDataRepository;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

@Service
public class BaptismDataService {
	
	@Autowired
	private BaptismDataRepository repository;
	
	public List<BaptismData> findAll() {
		return repository.findAll();
	}
	
	public BaptismData findById(Long id) {
		Optional<BaptismData> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<BaptismData> findByName(String nameChildren) {
		return repository.findByName(nameChildren);
	}
}
