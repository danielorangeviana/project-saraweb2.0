package com.br.saraweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb.entities.Godparent;

@Repository
public interface GodparentRepository extends JpaRepository<Godparent, Long> {
	
}
