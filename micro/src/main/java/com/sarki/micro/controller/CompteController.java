package com.sarki.micro.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.CompteCourant;
import com.sarki.micro.repository.ClientRepository;
import com.sarki.micro.repository.CompteCourantRepository;
import com.sarki.micro.repository.CompteRepository;

@RestController
@RequestMapping("/apicompte")
public class CompteController {
	

	@Autowired
	CompteCourantRepository compteRepo;
	@Autowired
	ClientRepository clientRepo;
		
	 // Create a new CompteCourant
	  @PostMapping("/comptecourant")
	  public CompteCourant createCompteCourant( @RequestParam long id ) {
		  CompteCourant cpt = new CompteCourant();
		  cpt.setSolde(0); // initialisation du solde a la creation
		  cpt.setClient(clientRepo.getOne(id));
		  System.out.println("\n\n"+ cpt.getClient().getNom()+"\n\n");
		  cpt.setCreatedAt(new Date());
		  cpt.setDecouvert(0); // initialisation du decouvert a la creation
		  cpt.setEmploye(null);
		  cpt.setUpdatedAt(new Date());
	      return compteRepo.save(cpt);
	  }
	  /*
	// Get All Client
		  @GetMapping("/clients")
		  public List<Client> getAllAgence() {
		      return clientRepo.findAll();
		  }
		  
	// Get a Single Client
	  @GetMapping("/client/{id}")
	  public Client getAgenceById(@PathVariable(value = "id") Long clientId) {
	      return clientRepo.findById(clientId)
	              .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
	  }
	  
	  
   // Delete a Client
	  @DeleteMapping("/client/{id}")
	  public ResponseEntity<?> deleteAgence(@PathVariable(value = "id") Long clientId) {
	      Client client = clientRepo.findById(clientId)
	              .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

	      clientRepo.delete(client);
	      return ResponseEntity.ok().build();
	}
*/

}
