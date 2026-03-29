package com.br.saraweb.tests;

import com.br.saraweb.entities.Book;

public class BookFactory {
	
	public static Book createBook() {
		return new Book(1L, 3, 50);
	}
	
	public static Book createBook(Long id, Integer numberBook, Integer numberOfPage) {
		return new Book(id, numberBook, numberOfPage);
	}
}
