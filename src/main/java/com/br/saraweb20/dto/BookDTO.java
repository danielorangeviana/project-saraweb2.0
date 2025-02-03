package com.br.saraweb20.dto;

import java.io.Serializable;

import com.br.saraweb20.entities.Book;

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
	
	@NotNull(message = "Entry the sheet number")
	private Integer numberSheet;
	
	public BookDTO(Book entity) {
		setId(entity.getId());
		numberBook = entity.getNumberBook();
		numberSheet = entity.getNumberSheet();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
