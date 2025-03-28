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
	
	@Query(value = """
		    SELECT 
		        u.cpf AS username, 
		        u.password AS password, 
		        r.id AS roleId, 
		        r.authority AS authority
		    FROM User u
		    JOIN u.roles r
		    WHERE u.cpf = :cpf
		    """)
	List<UserDetailsProjection> searchUserAndRolesByCpf(String cpf);
}
