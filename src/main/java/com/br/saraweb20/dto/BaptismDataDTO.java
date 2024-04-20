package com.br.saraweb20.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.entities.Book;
import com.br.saraweb20.entities.Celebrant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaptismDataDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Entry term number")
	private Long numberTerm;
	
	@NotBlank(message = "Entry the name of children")
	private String nameChildren;
	
	@PastOrPresent(message = "The date must be past or present")
	@NotNull(message = "Entry the child's date of birth")
	private LocalDate dateBirth;
	
	private String father;
	
	@NotBlank(message = "Entry the name of the child's mother")
	private String mother;
	
	@NotBlank(message = "Entry the name of the child's godfather")
	private String godFather;
	
	@NotBlank(message = "Entry the name of the child's godmother")
	private String godMother;
	
	@NotNull(message = "Entry baptism date")
	private LocalDate dateBaptism;
	
	private Book book;
	private Celebrant celebrant;
	
	public BaptismDataDTO(BaptismData entity) {
		id = entity.getId();
		numberTerm = entity.getNumberTerm();
		nameChildren = entity.getNameChildren();
		dateBirth = entity.getDateBirth();
		father = entity.getFather();
		mother = entity.getMother();
		godFather = entity.getGodFather();
		godMother = entity.getGodMother();
		dateBaptism = entity.getDateBaptism();
		book = entity.getBook();
		celebrant = entity.getCelebrant();
	}
}
