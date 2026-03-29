package com.br.saraweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb.entities.Celebrant;

@Repository
public interface CelebrantRepository extends JpaRepository<Celebrant, Long>{
	
}
