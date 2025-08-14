package com.br.saraweb20.service;

import java.util.Optional;

import com.br.saraweb20.mappers.BookMapper;
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
import com.br.saraweb20.exceptions.DatabaseException;
import com.br.saraweb20.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

	public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional(readOnly = true)
	public Page<BookDTO> findAllPaged(Pageable pageable) {
		Page<Book> list = bookRepository.findAll(pageable);
		return list.map(bookMapper::toDTO);
	}

	@Transactional(readOnly = true)
	public BookDTO findById(Long id) {
		Optional<Book> obj = bookRepository.findById(id);
		Book entity = obj.orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
		return bookMapper.toDTO(entity);
	}

	@Transactional
	public BookDTO insert(BookDTO dto) {
		Book entity = bookMapper.toEntity(dto);
		entity = bookRepository.save(entity);
		return bookMapper.toDTO(entity);
	}
	
	@Transactional
	public BookDTO update(Long id, BookDTO dto) {
		try {
			Book entity = bookRepository.getReferenceById(id);
			Book updatedEntity = bookMapper.toEntity(dto);
            entity.setNumberBook(updatedEntity.getNumberBook());
            entity.setNumberOfPage(updatedEntity.getNumberOfPage());

            entity = bookRepository.save(entity);
			return bookMapper.toDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Book not found!");
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!bookRepository.existsById(id))
			throw new ResourceNotFoundException("Book not found!");
		
		try {
            bookRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException("Referential integrity failure");
		}
	}

}
