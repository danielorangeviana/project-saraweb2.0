package com.br.saraweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb.entities.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
	
}
