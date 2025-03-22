package com.br.saraweb20.entities;

import java.io.Serializable;
import java.time.LocalDate;

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
	private Long id;
	
	private Long numberTerm;
	private String nameChildren;
	private LocalDate dateBirth;
	private LocalDate dateBaptism;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Parent parents;
	
	@ManyToOne
	@JoinColumn(name = "godparent_id")
	private Godparent godparents;
	
	@ManyToOne
	@JoinColumn(name = "pageBook_id")
	private PageBook pageBook;
	
	@ManyToOne
	@JoinColumn(name = "celebrant_id")
	private Celebrant celebrant;
}
