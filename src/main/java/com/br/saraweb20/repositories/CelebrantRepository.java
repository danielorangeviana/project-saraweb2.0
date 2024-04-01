package com.br.saraweb20.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.Celebrant;

@Repository
public interface CelebrantRepository extends JpaRepository<Celebrant, Long>{
	
	@Query(value = "select c from Celebrant c where upper(trim(c.name)) like %?1%")
	List<Celebrant> findByName(String name);
}
