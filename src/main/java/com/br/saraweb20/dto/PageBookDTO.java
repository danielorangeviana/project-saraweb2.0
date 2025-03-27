package com.br.saraweb20.dto;

import java.io.Serializable;

import com.br.saraweb20.entities.PageBook;

import jakarta.validation.constraints.NotNull;
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
public class PageBookDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Entry the page number")
	private Integer pageNumber;
	
	@NotNull(message = "Number of book is required")
	private Long pageBookId;
	
	public PageBookDTO(PageBook entity) {
		id = entity.getId();
		pageNumber = entity.getPageNumber();
		pageBookId = entity.getBook().getId();
	}

}
