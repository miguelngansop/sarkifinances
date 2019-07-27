package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.Agence;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {

}
