package com.br.saraweb20.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.BaptismData;

@Repository
public interface BaptismDataRepository extends JpaRepository<BaptismData, Long>{
	
	Page<BaptismData> findAll(Pageable pageable);
	
	@Query(value = "select bd from BaptismData bd where upper(trim(bd.nameChildren)) like %?1%")
	List<BaptismData> findByName(String nameChildren);
	
}
