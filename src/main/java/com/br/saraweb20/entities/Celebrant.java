package com.br.saraweb20.entities;

import java.io.Serializable;
import java.util.Objects;

import com.br.saraweb20.entities.enums.ReligiousTitle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_celebrant")
public class Celebrant implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME", length = 90, nullable = false, unique = false)
	private String name;
	
	private Integer religiousTitle;

	public Celebrant() {
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celebrant other = (Celebrant) obj;
		return Objects.equals(id, other.id);
	}
}
