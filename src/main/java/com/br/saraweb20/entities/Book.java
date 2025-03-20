package com.br.saraweb20.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_book")
public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer numberBook;
	
	private Integer numberOfPage;
	
	@OneToMany(mappedBy = "book")
	@JsonIgnore
	private List<PageBook> pages = new ArrayList<>();

	public Book(Long id, Integer numberBook, Integer numberOfPage) {
		this.id = id;
		this.numberBook = numberBook;
		this.numberOfPage = numberOfPage;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNumberBook(Integer numberBook) {
		this.numberBook = numberBook;
	}

	public void setNumberOfPage(Integer numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

}
