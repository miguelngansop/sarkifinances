package com.sarki.micro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sarki.micro.model.Client;
import com.sarki.micro.repository.ClientRepository;

import exception.ResourceNotFoundException;

@RestController
@RequestMapping("/apiclient")
public class ClientController {
	
	@Autowired
	ClientRepository clientRepo;
		
	 // Create a new Client
	  @PostMapping("/client")
	  public Client createAgence(@Valid @RequestBody Client cl) {
	      return clientRepo.save(cl);
	  }
	  
	  // Get All Client
	  @GetMapping("/clients")
	  public List<Client> getAllClient() {
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
	    @PutMapping("/client/{id}")
	    public Client updateClient(@PathVariable(value = "id") Long clientId,
	                                           @Valid @RequestBody Client clientDetails) {
	        Client cl = clientRepo.findById(clientId)
	        .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
	        cl.setCartedesejour(clientDetails.getCartedesejour());
	        cl.setDateDeNaissance(clientDetails.getDateDeNaissance());
	        cl.setEmail(clientDetails.getEmail());
	        cl.setLieuDeNaissance(clientDetails.getLieuDeNaissance());
	        cl.setNom(clientDetails.getNom());
	        cl.setNomDeLaMere(clientDetails.getNomDeLaMere());
	        cl.setNomDuPere(clientDetails.getNomDuPere());
	        cl.setNumcni(clientDetails.getNumcni());
	        cl.setPassport(clientDetails.getPassport());
	        cl.setPhoto(clientDetails.getPhoto());
	        cl.setPrenom(clientDetails.getPrenom());
	        cl.setProfession(clientDetails.getProfession());
	        cl.setResidence(clientDetails.getResidence());
	        cl.setTelephone(clientDetails.getTelephone());
	        Client updatedClient = clientRepo.save(cl);
	        return updatedClient;
	    }

}
