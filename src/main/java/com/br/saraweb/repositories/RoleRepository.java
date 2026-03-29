package com.br.saraweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.saraweb.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
