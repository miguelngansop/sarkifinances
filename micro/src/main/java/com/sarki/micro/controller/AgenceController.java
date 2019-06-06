package com.sarki.micro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.Agence;
import com.sarki.micro.repository.AgenceRepository;

import exception.ResourceNotFoundException;

@RestController
@RequestMapping("/apiagence")
public class AgenceController {
	
	@Autowired
	AgenceRepository agRepo;
	  // Create a new Employe
	
	 // Create a new Agence
	  @PostMapping("/agence")
	  public Agence createAgence(@Valid @RequestBody Agence a) {
	      return agRepo.save(a);
	  }
	  
	// Get All Agences
		  @GetMapping("/agences")
		  public List<Agence> getAllAgence() {
		      return agRepo.findAll();
		  }
		  
	// Get a Single Agence
	  @GetMapping("/agence/{id}")
	  public Agence getAgenceById(@PathVariable(value = "id") Long agenceId) {
	      return agRepo.findById(agenceId)
	              .orElseThrow(() -> new ResourceNotFoundException("Agence", "id", agenceId));
	  }
	  
	  
   // Delete a Agence
	  @DeleteMapping("/agence/{id}")
	  public ResponseEntity<?> deleteAgence(@PathVariable(value = "id") Long agenceId) {
	      Agence agence = agRepo.findById(agenceId)
	              .orElseThrow(() -> new ResourceNotFoundException("Agence", "id", agenceId));

	      agRepo.delete(agence);
	      return ResponseEntity.ok().build();
	}


}
