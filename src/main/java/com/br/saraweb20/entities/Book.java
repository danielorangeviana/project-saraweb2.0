package com.br.saraweb20.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
	
	private Integer numberSheet;
	
	@OneToMany(mappedBy = "book")
	@JsonIgnore
	private Set<BaptismData> baptismData = new HashSet<>();

	public Book(Long id, Integer numberBook, Integer numberSheet) {
		this.id = id;
		this.numberBook = numberBook;
		this.numberSheet = numberSheet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumberBook(Integer numberBook) {
		this.numberBook = numberBook;
	}

	public void setNumberSheet(Integer numberSheet) {
		this.numberSheet = numberSheet;
	}
}
