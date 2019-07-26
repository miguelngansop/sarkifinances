package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

}
