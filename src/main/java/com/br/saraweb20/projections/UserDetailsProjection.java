package com.br.saraweb20.projections;

public interface UserDetailsProjection {
	
	String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();
}
