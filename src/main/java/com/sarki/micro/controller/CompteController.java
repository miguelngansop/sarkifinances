package com.sarki.micro.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.Client;
import com.sarki.micro.model.Commercants;
import com.sarki.micro.model.Compte;
import com.sarki.micro.model.CompteMonnaie;
import com.sarki.micro.model.Particulier;
import com.sarki.micro.model.Retrait;
import com.sarki.micro.model.Versement;
import com.sarki.micro.repository.ClientRepository;
import com.sarki.micro.repository.CompteRepository;
import com.sarki.micro.repository.OperationRepository;

import exception.ResourceNotFoundException;
import javassist.expr.Instanceof;

@RestController
@RequestMapping("/apicompte")
public class CompteController {

	@Autowired
	CompteRepository compteRepo;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	OperationRepository opRepo;
	// Create a new CompteCourant

	// Create a new CompteCollecte	// Get All Comptes
	@GetMapping("/comptes")
	public List<Compte> getAllCompte() {
		return compteRepo.findAll();
	}

	// Get a Single Compte
	@GetMapping("/compte/{id}")
	public Compte getCompteById(@PathVariable(value = "id") Long compteId) {
		return compteRepo.findById(compteId).orElseThrow(() -> new ResourceNotFoundException("Compte", "id", compteId));
	}

	// creation d'un compte Monnaie

	@PostMapping("/add/{id}/{code}")
	public Compte add(@PathVariable(value="id") Long clientId,@PathVariable(value="code") Long code ,@Valid @RequestBody CompteMonnaie compte) {
		if (clientRepo.existsById(clientId)) {
			Client cl = clientRepo.getOne(clientId);
			compte.setCreatedAt(new Date());
			compte.setUpdatedAt(new Date());
			compte.setSolde(0);
			if(code == 1) {				// particulier
				Particulier p = new Particulier();
				p.setId(clientId);
				p.setEmail(cl.getEmail());
				p.setNom(cl.getNom());
				p.setPhoto(cl.getPhoto());
				compte.setClient(p);
			}else{
				Commercants cmt = new Commercants();
				cmt.setId(clientId);
				cmt.setEmail(cl.getEmail());
				cmt.setNom(cl.getNom());
				compte.setClient(cmt);
				
			}
			return compteRepo.save(compte);

		}else {
			return null;
		}
	}


	// versement

	@PatchMapping("/compte/{id}")
	public ResponseEntity<?> versement(@PathVariable(value = "id") Long compteId, @Valid @RequestBody Versement details)
			throws ResourceNotFoundException {
		Compte compte = compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("CompteCourant", "id", compteId));
		compte.setSolde(compte.getSolde() + details.getMontant());
		final Compte updatedCompte = compteRepo.save(compte);
		details.setCompte(compte);
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		opRepo.save(details);
		return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
	}

	// retrait

	@PatchMapping("/compte/retrait/{id}")
	public ResponseEntity<?> retrait(@PathVariable(value = "id") Long compteId, @Valid @RequestBody Retrait details)
			throws ResourceNotFoundException {
		Compte compte = compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("Compte", "id", compteId));
		if (compte.getSolde() >= details.getMontant()) {
			compte.setSolde(compte.getSolde() - details.getMontant());
			final Compte updatedCompte = compteRepo.save(compte);
			details.setCompte(compte);
			details.setCreatedAt(new Date());
			details.setUpdatedAt(new Date());
			opRepo.save(details);
			return ResponseEntity.ok(updatedCompte);

		} else {
			return ResponseEntity.ok(compte);
		}

	}
}