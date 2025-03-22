package com.br.saraweb20.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.br.saraweb20.entities.BaptismData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaptismDataDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "The term number is required.")
	private Long numberTerm;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Enter the full name of the child")
	private String nameChildren;
	
	@PastOrPresent(message = "The date must be past or present")
	@NotNull(message = "Enter the child's date of birth")
	private LocalDate dateBirth;
	
	@NotNull(message = "Enter the baptism date")
	private LocalDate dateBaptism;
	
	private Long pageBookId;
	private Long parentId;
	private Long godparentId;
	private Long celebrantId;
	
	public BaptismDataDTO(BaptismData entity) { 
		this.id = entity.getId(); 
		this.numberTerm = entity.getNumberTerm(); 
		this.nameChildren = entity.getNameChildren(); 
		this.dateBirth = entity.getDateBirth(); 
		this.dateBaptism = entity.getDateBaptism(); 
		this.pageBookId = entity.getPageBook().getId();
		this.parentId = entity.getParents().getId();
		this.godparentId = entity.getGodparents().getId();
		this.celebrantId = entity.getCelebrant().getId();
	}
	
}
