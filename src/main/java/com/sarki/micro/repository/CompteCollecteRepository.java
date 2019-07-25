package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.CompteCollecte;

@Repository
public interface CompteCollecteRepository extends JpaRepository<CompteCollecte, Long>{

}
