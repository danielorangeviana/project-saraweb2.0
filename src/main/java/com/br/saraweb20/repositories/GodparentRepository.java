package com.br.saraweb20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.Godparent;

@Repository
public interface GodparentRepository extends JpaRepository<Godparent, Long> {
	
}
