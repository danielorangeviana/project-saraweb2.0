package com.br.saraweb20.entities;

import java.io.Serializable;

import com.br.saraweb20.entities.enums.ReligiousTitle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tb_celebrant")
public class Celebrant implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	@Column(name = "NAME", length = 90, nullable = false, unique = false)
	private String name;
	
	private Integer religiousTitle;

	public Celebrant(Long id, String name, ReligiousTitle religiousTitle) {
		super();
		this.id = id;
		this.name = name;
		setReligiousTitle(religiousTitle);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReligiousTitle getReligiousTitle() {
		return ReligiousTitle.valueOf(religiousTitle);
	}

	public void setReligiousTitle(ReligiousTitle religiousTitle) {
		if (religiousTitle != null) {
			this.religiousTitle = religiousTitle.getCode();
		}
	}
}
