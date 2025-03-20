package com.br.saraweb20.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.br.saraweb20.entities.BaptismData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaptismDataMinDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Entry term number")
	private Long numberTerm;
	
	@NotBlank(message = "Entry the name of children")
	private String nameChildren;
	
	@NotBlank(message = "Entry the name of the child's mother")
	private String mother;
	
	@NotNull(message = "Entry baptism date")
	private LocalDate dateBaptism;
	
	private PageBookDTO pageBook;
	private CelebrantDTO celebrant;
	
	public BaptismDataMinDTO(Long id, Long numberTerm, String nameChildren, String mother, LocalDate dateBaptism) {
		this.id = id;
		this.numberTerm = numberTerm;
		this.nameChildren = nameChildren;
		this.mother = mother;
		this.dateBaptism = dateBaptism;
	}
	
	public BaptismDataMinDTO(BaptismData entity) {
		id = entity.getId();
		numberTerm = entity.getNumberTerm();
		nameChildren = entity.getNameChildren();
		mother = entity.getMother();
		dateBaptism = entity.getDateBaptism();
		pageBook = new PageBookDTO(entity.getPageBook());
		celebrant = new CelebrantDTO(entity.getCelebrant());
	}
	
}
