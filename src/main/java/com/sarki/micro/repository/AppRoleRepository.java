package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long>  {
	
	public AppRole findByRole(String role);
}
 