package com.br.saraweb20.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.br.saraweb20.entities.BaptismData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaptismDataDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Entry term number")
	private Long numberTerm;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Entry the full name of children")
	private String nameChildren;
	
	@PastOrPresent(message = "The date must be past or present")
	@NotNull(message = "Entry the child's date of birth")
	private LocalDate dateBirth;
	
	private String father;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Entry the full name of the child's mother")
	private String mother;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Entry the full name of the child's godfather")
	private String godFather;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Entry the full name of the child's godmother")
	private String godMother;
	
	@NotNull(message = "Entry baptism date")
	private LocalDate dateBaptism;
	
	@NotNull(message = "Must have at least one book")
	private BookDTO book;
	
	@NotNull(message = "Must have at least one celebrant")
	private CelebrantDTO celebrant;
	
	public BaptismDataDTO(Long id, Long numberTerm, String nameChildren, LocalDate dateBirth, String father, String mother, String godFather, String godMother, LocalDate dateBaptism) {
		this.id = id;
		this.numberTerm = numberTerm;
		this.nameChildren = nameChildren;
		this.dateBirth = dateBirth;
		this.father = father;
		this.mother = mother;
		this.godFather = godFather;
		this.godMother = godMother;
		this.dateBaptism = dateBaptism;
	}
	
	public BaptismDataDTO(BaptismData entity) {
		id = entity.getId();
		numberTerm = entity.getNumberTerm();
		nameChildren = entity.getNameChildren();
		dateBirth = entity.getDateBirth();
		father = entity.getFather();
		mother = entity.getMother();
		godFather = entity.getGodfather();
		godMother = entity.getGodmother();
		dateBaptism = entity.getDateBaptism();
		book = new BookDTO(entity.getBook());
		celebrant = new CelebrantDTO(entity.getCelebrant());
	}
	
}
