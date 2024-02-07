package com.br.saraweb20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.saraweb20.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
