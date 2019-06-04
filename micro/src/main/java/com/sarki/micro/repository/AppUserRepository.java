package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.AppUser;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>  {

	public AppUser findByUsername(String username);

}
