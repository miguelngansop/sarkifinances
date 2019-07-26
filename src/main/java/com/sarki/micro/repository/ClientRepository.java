package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
