package com.sarki.micro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.Agence;
import com.sarki.micro.model.Employe;
import com.sarki.micro.repository.AgenceRepository;
import com.sarki.micro.repository.EmployeRepository;

@RestController
@RequestMapping("/apimicro")
public class EmployeController {
	
	@Autowired
	EmployeRepository emRepo;
	@Autowired
	AgenceRepository agRepo;
	  // Create a new Employe
	
	 // Get All Employes
	  @GetMapping("/employes")
	  public List<Employe> getAllEmployes() {
	      return emRepo.findAll();
	  }
	  
	 
	   // Create a new Agence
		  @PostMapping("/agence")
		  public Agence createAgence(@Valid @RequestBody Agence a) {
		      return agRepo.save(a);
		  }
	

}
