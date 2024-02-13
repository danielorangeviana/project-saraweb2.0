package com.br.saraweb20.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_term")
public class Term implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	@Column(name = "NUMBER_TERM", nullable = false, unique = false)
	private Integer numberTerm;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID", nullable = false)
	private Book book;

	public Term(Long id, Integer numberTerm, Book book) {
		super();
		this.id = id;
		this.numberTerm = numberTerm;
		this.book = book;
	}
}
