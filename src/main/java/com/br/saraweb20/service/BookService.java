package com.br.saraweb20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.BookDTO;
import com.br.saraweb20.entities.Book;
import com.br.saraweb20.repositories.BookRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	@Transactional(readOnly = true)
	public Page<BookDTO> findAllPaged(Pageable pageable) {
		Page<Book> list = repository.findAll(pageable);
		return list.map(element -> new BookDTO(element));
	}

	@Transactional(readOnly = true)
	public BookDTO findById(Long id) {
		Optional<Book> obj = repository.findById(id);
		Book entity = obj.orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
		return new BookDTO(entity);
	}

	@Transactional
	public BookDTO insert(BookDTO dto) {
		Book entity = new Book();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BookDTO(entity);
	}
	
	@Transactional
	public BookDTO update(Long id, BookDTO dto) {
		
		repository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
		
		try {
			Book entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new BookDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Book not found!");
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		repository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
		
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(BookDTO dto, Book entity) {

		entity.setNumberBook(dto.getNumberBook());
		entity.setNumberOfPage(dto.getNumberOfPage());
	}
}
