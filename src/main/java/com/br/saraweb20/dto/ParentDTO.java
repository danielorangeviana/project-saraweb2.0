package com.br.saraweb20.dto;

import java.io.Serializable;

import com.br.saraweb20.entities.Parent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class ParentDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String father;
	
	@Size(min = 3, max = 80, message = "Full name must be 3 to 80 characters long")
	@NotBlank(message = "Enter the full name of the child's mother")
	private String mother;
	
	public ParentDTO(Parent entity) {
		this.id = entity.getId();
		this.father = entity.getFather();
		this.mother = entity.getMother();
	}

}
