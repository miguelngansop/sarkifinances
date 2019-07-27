package com.sarki.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarki.micro.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

}
