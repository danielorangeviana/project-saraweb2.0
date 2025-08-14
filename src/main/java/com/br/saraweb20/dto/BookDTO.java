package com.br.saraweb20.dto;

import java.io.Serializable;

import com.br.saraweb20.entities.Book;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class BookDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Entry the book number")
	private Integer numberBook;
	
	@NotNull(message = "Entry the number of page")
	private Integer numberOfPage;

}
