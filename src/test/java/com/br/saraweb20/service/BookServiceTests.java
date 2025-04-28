package com.br.saraweb20.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.br.saraweb20.dto.BookDTO;
import com.br.saraweb20.entities.Book;
import com.br.saraweb20.repositories.BookRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;
import com.br.saraweb20.tests.BookFactory;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class BookServiceTests {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;
	
	private Long existingBookId, nonExistingBookId, dependentBookId;
	private Book book;
	private BookDTO bookDTO;
	private PageImpl<Book> page; 

	@BeforeEach
	void setUp() throws Exception {
		
		existingBookId = 1L;
		dependentBookId = 2L;
		nonExistingBookId = 30L;
		book = BookFactory.createBook();
		bookDTO = new BookDTO(book);
		page = new PageImpl<>(List.of(book));

		Mockito.when(bookRepository.findAll((Pageable) any())).thenReturn(page);
		
		Mockito.when(bookRepository.findById(existingBookId)).thenReturn(Optional.of(book));
		
		Mockito.when(bookRepository.save(any())).thenReturn(book);
		
		Mockito.when(bookRepository.getReferenceById(existingBookId)).thenReturn(book);
		Mockito.when(bookRepository.getReferenceById(nonExistingBookId)).thenThrow(EntityNotFoundException.class);
		
		Mockito.when(bookRepository.existsById(existingBookId)).thenReturn(true);
		Mockito.when(bookRepository.existsById(dependentBookId)).thenReturn(true);
		Mockito.when(bookRepository.existsById(nonExistingBookId)).thenReturn(false);
		
		Mockito.doNothing().when(bookRepository).deleteById(existingBookId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(bookRepository).deleteById(dependentBookId);
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<BookDTO> result = bookService.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getSize(), 1);
		Assertions.assertEquals(result.getContent().get(0).getId(), book.getId());
		Assertions.assertEquals(result.getContent().get(0).getNumberBook(), book.getNumberBook());
		Assertions.assertEquals(result.getContent().get(0).getNumberOfPage(), book.getNumberOfPage());
				

		Mockito.verify(bookRepository, times(1)).findAll(pageable);
	}
	
	@Test
	public void findByIdShouldReturnBookDTOWhenIdExists() {
		
		BookDTO result = bookService.findById(existingBookId);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingBookId);
		Assertions.assertEquals(result.getNumberBook(), book.getNumberBook());
		Assertions.assertEquals(result.getNumberOfPage(), book.getNumberOfPage());
		
	}
	
	@Test
	public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			bookService.findById(nonExistingBookId);
		});
	}
	
	@Test
	public void insertShouldReturnBookDTO() {
		
		BookDTO result = bookService.insert(bookDTO);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), bookDTO.getId());
		Assertions.assertEquals(result.getNumberBook(), bookDTO.getNumberBook());
		Assertions.assertEquals(result.getNumberOfPage(), bookDTO.getNumberOfPage());
	}
	
	@Test
	public void updateShouldReturnBookDTOWhenIdExists() {
		
		BookDTO result = bookService.update(existingBookId, bookDTO);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingBookId);
		Assertions.assertEquals(result.getNumberBook(), bookDTO.getNumberBook());
		Assertions.assertEquals(result.getNumberOfPage(), bookDTO.getNumberOfPage());
	}
	
	@Test
	public void updateShouldReturnResourseNotFoundExceptionWhenIdBookDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			bookService.update(nonExistingBookId, bookDTO);
		});
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			bookService.delete(existingBookId);
		});
	}
	
	@Test
	public void deleteShouldReturnResourseNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			bookService.delete(nonExistingBookId);
		});
	}
	
	@Test
	public void deleteShouldReturnDatabaseExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(DatabaseException.class, () -> {
			bookService.delete(dependentBookId);
		});
	}

}
