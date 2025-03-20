package com.br.saraweb20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.PageBookDTO;
import com.br.saraweb20.entities.Book;
import com.br.saraweb20.entities.PageBook;
import com.br.saraweb20.repositories.BookRepository;
import com.br.saraweb20.repositories.PageBookRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PageBookService {
	
	@Autowired
	private PageBookRepository pageBookRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Transactional(readOnly = true)
	public Page<PageBookDTO> findAllPaged(Pageable pageable) {
		Page<PageBook> list = pageBookRepository.findAll(pageable);
		return list.map(element -> new PageBookDTO(element));
	}

	@Transactional(readOnly = true)
	public PageBookDTO findById(Long id) {
		Optional<PageBook> obj = pageBookRepository.findById(id);
		PageBook entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new PageBookDTO(entity);
	}

	@Transactional
	public PageBookDTO insert(PageBookDTO dto) {
		PageBook entity = new PageBook();
		copyDtoToEntity(dto, entity);
		entity = pageBookRepository.save(entity);
		return new PageBookDTO(entity);
	}
	
	@Transactional
	public PageBookDTO update(Long id, PageBookDTO dto) {
		
		pageBookRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException(id));
		
		try {
			PageBook entity = pageBookRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = pageBookRepository.save(entity);
			return new PageBookDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		pageBookRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException(id));
		
		try {
			pageBookRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(PageBookDTO dto, PageBook entity) {

		entity.setPageNumber(dto.getPageNumber());
		
		Book book = bookRepository.findById(dto.getPageBookId())
				.orElseThrow(() -> new ResourceNotFoundException(dto.getId()));
		entity.setBook(book);
	}
}
