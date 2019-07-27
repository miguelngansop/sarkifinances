package com.sarki.micro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.Agence;
import com.sarki.micro.model.Agent;
import com.sarki.micro.repository.AgenceRepository;
import com.sarki.micro.repository.AgentRepository;

@RestController
@RequestMapping("/config")
public class ConfigController {
	@Autowired
	AgentRepository agentRepo;
	@Autowired
	AgenceRepository agenceRepo;
	
	// Create a new Agent
		  @PostMapping("/agent/{id}")
		  public Agent createAgent(@Valid @RequestBody Agent a,
				  @PathVariable(value="id") Long agenceId) {
			  Agence agence = agenceRepo.getOne(agenceId);
			  a.setAgence(agence);
		      return agentRepo.save(a);
		  }
		  
	// Create a new Agence
		  @PostMapping("/agence")
		  public Agence createAgence(@Valid @RequestBody Agence agce) {
		      return agenceRepo.save(agce);
		  }
	
	

}
