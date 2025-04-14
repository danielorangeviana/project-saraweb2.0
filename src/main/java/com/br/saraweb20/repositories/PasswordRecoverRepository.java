package com.br.saraweb20.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.saraweb20.entities.PasswordRecover;

@Repository
public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long>{
	
	// Search for valid tokens
	@Query("SELECT object FROM PasswordRecover object WHERE object.token = :token AND object.expiration > :now")
	List<PasswordRecover> searchValidToken(String token, Instant now);
}
