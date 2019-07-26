package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.CompteCourant;

@Repository
public interface CompteCourantRepository extends JpaRepository<CompteCourant, Long> {

}
