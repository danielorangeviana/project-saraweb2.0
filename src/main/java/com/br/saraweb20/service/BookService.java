package com.br.saraweb20.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.saraweb20.entities.Book;
import com.br.saraweb20.repositories.BookRepository;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	public List<Book> findAll() {
		return repository.findAll();
	}
	
	public Book findById(Long id) {
		Optional<Book> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Book insert (Book obj) {
		return repository.save(obj);
	}
}
