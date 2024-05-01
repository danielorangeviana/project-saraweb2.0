package com.br.saraweb20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
