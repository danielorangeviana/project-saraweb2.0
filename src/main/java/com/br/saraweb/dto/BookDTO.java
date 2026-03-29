package com.br.saraweb.dto;

import java.io.Serializable;

import com.br.saraweb.entities.Book;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BookDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Entry the book number")
	private Integer numberBook;
	
	@NotNull(message = "Entry the number of page")
	private Integer numberOfPage;
	
	public BookDTO(Book entity) {
		id = entity.getId();
		numberBook = entity.getNumberBook();
		numberOfPage = entity.getNumberOfPage();
	}

}
