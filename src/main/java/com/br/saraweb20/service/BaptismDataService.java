package com.br.saraweb20.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.repositories.BaptismDataRepository;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

@Service
public class BaptismDataService {
	
	@Autowired
	private BaptismDataRepository repository;
	
	private static final int PAGE_SIZE = 5;
	
	public Page<BaptismData> findAll(int numPage) {
		Pageable pageable = PageRequest.of(numPage-1, PAGE_SIZE);
		return repository.findAll(pageable);
	}
	
	public BaptismData findById(Long id) {
		Optional<BaptismData> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<BaptismData> findByName(String nameChildren) {
		return repository.findByName(nameChildren);
	}
	
	public BaptismData insert(BaptismData obj) {
		return repository.save(obj);
	}
}
