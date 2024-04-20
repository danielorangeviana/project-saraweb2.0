package com.br.saraweb20.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tb_baptism_data")
public class BaptismData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_BAPTISM")
	private Long id;
	
	@Column(name = "TERM")
	private Long numberTerm;
	
	@Column(name = "NAME_CHILDREN")
	private String nameChildren;
	
	@Column(name = "DATE_BIRTH")
	private LocalDate dateBirth;
	
	@Column(name = "FATHER")
	private String father;
	
	@Column(name = "MOTHER")
	private String mother;
	
	@Column(name = "GOD_FATHER")
	private String godFather;
	
	@Column(name = "GOD_MOTHER")
	private String godMother;
	
	@Column(name = "DATE_BAPTISM")
	private LocalDate dateBaptism;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CELEBRANT_ID")
	private Celebrant celebrant;
}
