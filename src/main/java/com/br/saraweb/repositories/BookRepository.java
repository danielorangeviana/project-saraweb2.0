package com.br.saraweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
}
