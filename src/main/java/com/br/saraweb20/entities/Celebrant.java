package com.br.saraweb20.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.br.saraweb20.entities.enums.ReligiousTitle;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	private Integer religiousTitle;
	
	@OneToMany(mappedBy = "celebrant", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<BaptismData> baptismData = new HashSet<>();

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
