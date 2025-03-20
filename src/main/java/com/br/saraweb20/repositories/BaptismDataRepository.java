package com.br.saraweb20.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.BaptismData;

@Repository
public interface BaptismDataRepository extends JpaRepository<BaptismData, Long>{
	
	@Query(value = "SELECT obj FROM BaptismData obj JOIN FETCH obj.pageBook JOIN FETCH obj.celebrant " +
			"WHERE UPPER(obj.nameChildren) LIKE UPPER(CONCAT('%', :nameChildren, '%'))")
	Page<BaptismData> searchByName(String nameChildren, Pageable pageable);
}
