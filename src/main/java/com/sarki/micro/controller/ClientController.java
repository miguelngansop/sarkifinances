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

import com.sarki.micro.model.Client;
import com.sarki.micro.model.Commercants;
import com.sarki.micro.model.Particulier;
import com.sarki.micro.repository.ClientRepository;

import exception.ResourceNotFoundException;

@RestController
@RequestMapping("/apiclient")
public class ClientController {

	@Autowired
	ClientRepository clientRepo;

	// Create a new Client Particulier
	@PostMapping("/particulier")
	public Particulier createAgence(@Valid @RequestBody Particulier cl) {
		return clientRepo.save(cl);
	}

	// Create commercant
	@PostMapping("/commercant")
	public Commercants createAgence(@Valid @RequestBody Commercants cl) {
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
		return clientRepo.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
	}

	// Delete a Client
	@DeleteMapping("/client/{id}")
	public ResponseEntity<?> deleteAgence(@PathVariable(value = "id") Long clientId) {
		Client client = clientRepo.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

		clientRepo.delete(client);
		return ResponseEntity.ok().build();
	}

}
