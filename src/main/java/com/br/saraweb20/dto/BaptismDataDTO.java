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
	
	@NotNull(message = "The term number is required.")
	private Long numberTerm;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Enter the full name of the child")
	private String nameChildren;
	
	@PastOrPresent(message = "The date must be past or present")
	@NotNull(message = "Enter the child's date of birth")
	private LocalDate dateBirth;
	
	private String father;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Enter the full name of the child's mother")
	private String mother;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Enter the full name of the child's godfather")
	private String godfather;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Enter the full name of the child's godmother")
	private String godmother;
	
	@NotNull(message = "Enter the baptism date")
	private LocalDate dateBaptism;
	
	@NotNull(message = "A book is required")
	private Long pageBookId;
	
	@NotNull(message = "A celebrant is required")
	private Long celebrantId;
	
	public BaptismDataDTO(Long id, Long numberTerm, String nameChildren, LocalDate dateBirth, 
			String father, String mother, String godfather, String godmother, LocalDate dateBaptism,
			Long pageBookId, Long celebrantId) {
		
		this.id = id;
		this.numberTerm = numberTerm;
		this.nameChildren = nameChildren;
		this.dateBirth = dateBirth;
		this.father = father;
		this.mother = mother;
		this.godfather = godfather;
		this.godmother = godmother;
		this.dateBaptism = dateBaptism;
		this.pageBookId = pageBookId;
		this.celebrantId = celebrantId;		
	}
	
	public BaptismDataDTO(BaptismData entity) { 
		id = entity.getId(); 
		numberTerm = entity.getNumberTerm(); 
		nameChildren = entity.getNameChildren(); 
		dateBirth = entity.getDateBirth(); 
		father = entity.getFather(); 
		mother = entity.getMother(); 
		godfather = entity.getGodfather(); 
		godmother = entity.getGodmother(); 
		dateBaptism = entity.getDateBaptism(); 
		pageBookId = entity.getPageBook().getId();
		celebrantId = entity.getCelebrant().getId();
	}
	
}
