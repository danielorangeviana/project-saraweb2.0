package com.br.saraweb20.dto;

import java.io.Serializable;

import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.entities.enums.ReligiousTitle;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CelebrantDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long idCelebrant;
	
	@NotBlank(message = "Entry the name of celebrant")
	private String nameCelebrant;
	
	private ReligiousTitle religiousTitle;
	
	public CelebrantDTO(Celebrant entity) {
		idCelebrant = entity.getId();
		nameCelebrant = entity.getName();
		religiousTitle = entity.getReligiousTitle();
	}
}
