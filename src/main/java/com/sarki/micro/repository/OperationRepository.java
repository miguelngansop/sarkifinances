package com.sarki.micro.repository;


import com.sarki.micro.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByCompteIdOrderByCreatedAtDesc(Long idExpediteur);
}
