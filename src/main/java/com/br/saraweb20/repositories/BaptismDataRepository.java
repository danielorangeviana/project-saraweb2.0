package com.br.saraweb20.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.BaptismData;

@Repository
public interface BaptismDataRepository extends JpaRepository<BaptismData, Long>{
	
	/*
	@Query(value = "SELECT obj FROM BaptismData obj JOIN FETCH obj.pageBook " 
			+ "JOIN FETCH obj.parent JOIN FETCH obj.celebrant "
			+ "WHERE UPPER(obj.nameChildren) LIKE UPPER(CONCAT('%', :nameChildren, '%'))",
			countQuery = "SELECT COUNT(obj) FROM BaptismData JOIN obj.pageBook "
			+ "JOIN obj.parent JOIN obj.celebrant"
		  )
	Page<BaptismData> searchByName(String nameChildren, Pageable pageable);
	*/
	@EntityGraph(attributePaths = {"parents", "pageBook", "celebrant"})
    @Query("SELECT obj FROM BaptismData obj WHERE UPPER(obj.nameChildren) LIKE UPPER(CONCAT('%', :nameChildren, '%'))")
	Page<BaptismData> searchByName(String nameChildren, Pageable pageable);
}
