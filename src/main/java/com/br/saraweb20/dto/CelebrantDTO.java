package com.br.saraweb20.dto;

import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.entities.enums.ReligiousTitle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CelebrantDTO {
	
	private Long id;
	
	@NotBlank(message = "Enter the name of celebrant")
	private String name;
	
	@NotNull(message = "Enter the Religious title")
	private ReligiousTitle religiousTitle;
	
	public CelebrantDTO(Celebrant entity) {
		id = entity.getId();
		name = entity.getName();
		religiousTitle = entity.getReligiousTitle();
	}
}
