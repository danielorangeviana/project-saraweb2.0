package com.br.saraweb20.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Column(name = "ID_BAPTISM", unique = true)
	private Long id;
	
	@Column(name = "TERM", nullable = false, unique = true)
	private Long numberTerm;
	
	@Column(name = "NAME_CHILDREN", length = 90, nullable = false, unique = false)
	private String nameChildren;
	
	@Column(name = "DATE_BIRTH", nullable = false, unique = false)
	private LocalDate dateBirth;
	
	@Column(name = "DADDY", length = 90, nullable = true, unique = false)
	private String daddy;
	
	@Column(name = "MOM", length = 90, nullable = false, unique = false)
	private String mom;
	
	@Column(name = "GOD_FATHER", length = 90, nullable = false, unique = false)
	private String godFather;
	
	@Column(name = "GOD_MOTHER", length = 90, nullable = false, unique = false)
	private String godMother;
	
	@Column(name = "DATE_BAPTISM", nullable = false, unique = false)
	private LocalDate dateBaptism;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "CELEBRANT_ID")
	private Celebrant celebrant;
}
