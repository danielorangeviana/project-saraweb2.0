package com.br.saraweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb.entities.PageBook;

@Repository
public interface PageBookRepository extends JpaRepository<PageBook, Long> {
	
}
