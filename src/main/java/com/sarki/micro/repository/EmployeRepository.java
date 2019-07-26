package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long>{

}
