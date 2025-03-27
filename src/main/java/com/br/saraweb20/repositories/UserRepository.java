package com.br.saraweb20.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.User;
import com.br.saraweb20.projections.UserDetailsProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	User findByCpf(String cpf);
	
	@Query("SELECT user.cpf AS username, user.password, role.id AS roleId, role.authority " +
		       "FROM User user " +
		       "JOIN user.roles role " +
		       "WHERE user.cpf = :cpf")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String cpf);
}
