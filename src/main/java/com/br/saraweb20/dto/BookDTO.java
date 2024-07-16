package com.br.saraweb20.dto;

import java.io.Serializable;

import com.br.saraweb20.entities.Book;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Entry the book number")
	private Integer numberBook;
	
	@NotNull(message = "Entry the sheet number")
	private Integer numberSheet;
	
	public BookDTO(Book entity) {
		id = entity.getId();
		numberBook = entity.getNumberBook();
		numberSheet = entity.getNumberSheet();
	}
}
