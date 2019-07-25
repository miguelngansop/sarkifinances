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
import com.sarki.micro.model.CompteEpargne;
import com.sarki.micro.model.Retrait;
import com.sarki.micro.model.Versement;
import com.sarki.micro.repository.ClientRepository;
import com.sarki.micro.repository.CompteRepository;
import com.sarki.micro.repository.OperationRepository;

import exception.ResourceNotFoundException;

@RestController
@RequestMapping("/apicompteEpargne")
public class CompteEpargneController {
	
	@Autowired
	CompteRepository compteRepo;
	
	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	OperationRepository opRepo;
	// Create a new CompteCourant
	// Create a new CompteEpargne

	@PostMapping("/compteEpargne/{id}")
	public CompteEpargne createCompteEpargne( @PathVariable(value = "id") Long clientId,
			@Valid @RequestBody Long montant ) {
		
	if(montant >= CompteEpargne.getFraisDeCreation() + CompteEpargne.getSoldeMin()) {
		CompteEpargne cpt = new CompteEpargne();
		if (clientRepo.existsById(clientId)) {
			cpt.setCreatedAt(new Date());
			cpt.setUpdatedAt(new Date());
			Client  cl = new Client();
			cl.setId(clientId);
			
			cl.setEmail(clientRepo.getOne(clientId).getEmail());
			cl.setDateDeNaissance(clientRepo.getOne(clientId).getDateDeNaissance());
			cl.setNom(clientRepo.getOne(clientId).getNom());
			cl.setNomDeLaMere(clientRepo.getOne(clientId).getNomDeLaMere());
			cl.setNomDuPere(clientRepo.getOne(clientId).getNomDuPere());
			cl.setNumcni(clientRepo.getOne(clientId).getNumcni());
			cl.setProfession(clientRepo.getOne(clientId).getProfession());
			cl.setResidence(clientRepo.getOne(clientId).getResidence());
			cl.setTelephone(clientRepo.getOne(clientId).getTelephone());  

			cpt.setClient(cl);
			Versement v = new Versement();
			CompteEpargne cptE = new CompteEpargne();
			cpt.setSolde(montant-CompteEpargne.getFraisDeCreation());						 // initialisation du solde a la creation
			cptE = compteRepo.save(cpt);		// recuperation du compte cree
												// versement du reste dans le compte
			v.setCompte(cptE);
			v.setCreatedAt(new Date());
			v.setUpdatedAt(new Date());
			v.setMontant(montant-CompteEpargne.getFraisDeCreation());
			opRepo.save(v);						 // operation valide
			
			return cptE;
		}else {
			return null;
		}
	}else {return null;}
	}

	//retrait epargne

	@PatchMapping("/compte/retrait/{id}")
	public ResponseEntity<?> retrait(@PathVariable(value = "id") Long compteId,
			@Valid @RequestBody Retrait details) throws ResourceNotFoundException {
		CompteEpargne compte =  (CompteEpargne) compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("CompteEpargne", "id", compteId));
		
		if(compte.getSolde() >= details.getMontant() && (CompteEpargne.getSoldeMin() <= compte.getSolde()-details.getMontant() ) ) {
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
