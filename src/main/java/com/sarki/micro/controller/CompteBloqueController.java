package com.sarki.micro.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.Client;
import com.sarki.micro.model.Compte;
import com.sarki.micro.model.CompteBloque;
import com.sarki.micro.model.Retrait;
import com.sarki.micro.model.Versement;
import com.sarki.micro.repository.ClientRepository;
import com.sarki.micro.repository.CompteRepository;
import com.sarki.micro.repository.OperationRepository;

import exception.ResourceNotFoundException;

@RestController
@RequestMapping("/apiCompteBloque")
public class CompteBloqueController {

	
	@Autowired
	CompteRepository compteRepo;
	
	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	OperationRepository opRepo;
	// Create a new CompteCourant
	// Create a new CompteEpargne

	@PostMapping("/compteBloque/{id}")
	public CompteBloque createCompteBloque( @PathVariable(value = "id") Long clientId,
			@Valid @RequestBody Long montant ) {
		
	if(montant >= CompteBloque.getFraisDeCreation() + CompteBloque.getSoldeMin()) {
		CompteBloque cpt = new CompteBloque();
		if (clientRepo.existsById(clientId)) {
			cpt.setCreatedAt(new Date());
			cpt.setUpdatedAt(new Date());
			Client  cl = new Client();
			cl.setId(clientId);
			cpt.setClient(cl);
			Versement v = new Versement();
			CompteBloque cptB = new CompteBloque();
			cpt.setSolde(montant-CompteBloque.getFraisDeCreation());						 // initialisation du solde a la creation
			cptB = compteRepo.save(cpt);		// recuperation du compte cree
												// versement du reste dans le compte
			v.setCompte(cptB);
			v.setCreatedAt(new Date());
			v.setUpdatedAt(new Date());
			v.setMontant(montant-CompteBloque.getFraisDeCreation());
			opRepo.save(v);						 // operation valide
			
			return cptB;
		}else {
			return null;
		}
	}else {return null;}
	}

	//retrait epargne

	@PatchMapping("/compte/retrait/{id}")
	public ResponseEntity<?> retrait(@PathVariable(value = "id") Long compteId,
			@Valid @RequestBody Retrait details) throws ResourceNotFoundException {
		CompteBloque compte =  (CompteBloque) compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("CompteBloque", "id", compteId));
		
		if(compte.getSolde() >= details.getMontant() && (CompteBloque.getSoldeMin() <= compte.getSolde()-details.getMontant() ) ) {
			compte.setSolde(compte.getSolde() - details.getMontant());
			final Compte updatedCompte = compteRepo.save(compte);
			details.setCompte(compte);
			details.setCreatedAt(new Date());
			details.setUpdatedAt(new Date());
			opRepo.save(details);
			return ResponseEntity.ok(updatedCompte);
			
		}else {
			return ResponseEntity.ok(compte);
		}

	}
	



	

}
