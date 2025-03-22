package com.br.saraweb20.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.entities.Parent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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
	
	@NotNull(message = "Entry baptism date")
	private LocalDate dateBaptism;
	
	private Parent parents;	
	private PageBookDTO pageBook;
	private CelebrantDTO celebrant;
	
	public BaptismDataMinDTO(BaptismData entity) {
		this.id = entity.getId();
		this.numberTerm = entity.getNumberTerm();
		this.nameChildren = entity.getNameChildren();
		this.parents = entity.getParents();
		this.dateBaptism = entity.getDateBaptism();
		this.pageBook = new PageBookDTO(entity.getPageBook());
		this.celebrant = new CelebrantDTO(entity.getCelebrant());
	}
	
}
