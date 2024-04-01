package com.br.saraweb20.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "select u from User u where upper(trim(u.name)) like %?1%")
	List<User> findByName(String name);
	
}
