package com.sarki.micro.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sarki.micro.model.Agent;
import com.sarki.micro.model.Compte;
import com.sarki.micro.model.Retrait;
import com.sarki.micro.model.Transfert;
import com.sarki.micro.model.Versement;
import com.sarki.micro.repository.AgentRepository;
import com.sarki.micro.repository.ClientRepository;
import com.sarki.micro.repository.CompteRepository;
import com.sarki.micro.repository.OperationRepository;

import exception.ResourceNotFoundException;

@RestController
@RequestMapping("/monnaie")
public class OperationsMonnaieCtrl {

	@Autowired
	CompteRepository compteRepo;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	OperationRepository opRepo;

	@Autowired
	AgentRepository agentRepo;
	// Create a new CompteCourant

	// retrait

	@PatchMapping("/retrait/{idC}/{idAg}")
	public ResponseEntity<?> retrait(@PathVariable(value = "idC") Long compteId,
			@PathVariable(value = "idAg") Long agentId, @Valid @RequestBody Retrait details)
					throws ResourceNotFoundException {
		Compte compte = compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("Compte", "id", compteId));

		Agent agent = agentRepo.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent", "id", agentId));
		System.out.println(agent.getNoms());
		if (compte.getSolde() >= details.getMontant()) {
			details.setAgent(agent);
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

	// versement

	@PatchMapping("/compte/{idC}/{idAg}")
	public ResponseEntity<?> versement(@PathVariable(value = "idC") Long compteId,
			@PathVariable(value = "idAg") Long agentId, @Valid @RequestBody Versement details)
					throws ResourceNotFoundException {
		Compte compte = compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("CompteCourant", "id", compteId));

		Agent agent = agentRepo.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent", "id", agentId));
		details.setAgent(agent);

		compte.setSolde(compte.getSolde() + details.getMontant());
		final Compte updatedCompte = compteRepo.save(compte);
		details.setCompte(compte);
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		opRepo.save(details);
		return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
	}
	
	// transfert
	/*
	@PatchMapping("/transfert/{idExpediteur}/{idBeneficiaire}")
	public Operation transfert(
			@PathVariable(value="idExpediteur") Long idExpediteur,
			@PathVariable(value="idBeneficiaire") Long idBeneficiaire,
			@Valid @RequestBody Transfert transert) {
			Retrait auxRetrait = new Retrait();
			auxRetrait.setCompte(compteRepo.getOne(idExpediteur));
			auxRetrait.setMontant(transert.getMontant());
			auxRetrait.setCreatedAt(new Date());
			auxRetrait.setUpdatedAt(new Date());
			auxRetrait.setId(0);
			Long PreSoldeExpe = (long) compteRepo.getOne(idExpediteur).getSolde();
			this.retrait(idExpediteur, null, auxRetrait);
			Long PostSoldeExp = (long) compteRepo.getOne(idExpediteur).getSolde();
				if (PostSoldeExp != PreSoldeExpe && (PostSoldeExp == PreSoldeExpe - transert.getMontant()) ) {
					Long PreSoldeBenef = (long) compteRepo.getOne(idBeneficiaire).getSolde();
					this.versement(idBeneficiaire, null, new Versement(0, transert.getMontant(), new Date(), new Date()));
					Long PostSoldeBenef = (long) compteRepo.getOne(idBeneficiaire).getSolde();
					if(PreSoldeBenef !=PostSoldeBenef && (PostSoldeBenef == PreSoldeBenef + transert.getMontant())) {
						//sucess
						return opRepo.save(transert);
						
					}else {
						// annule tous
					}
				}else {
					//annule le retrait
				}
		return null;
	}*/
	//
	
	@PatchMapping("/transfert/{idExpediteur}/{idBeneficiaire}")
	public ResponseEntity<?> transfert(
			@PathVariable(value="idExpediteur") Long idExpediteur,
			@PathVariable(value="idBeneficiaire") Long idBeneficiaire,
			@Valid @RequestBody Transfert transert) {
		
		Compte compte = compteRepo.findById(idExpediteur)
				.orElseThrow(() -> new ResourceNotFoundException("Compte", "id", idExpediteur));
		if (compte.getSolde() >= transert.getMontant()) {
			compte.setSolde(compte.getSolde() - transert.getMontant());
			final Compte updatedCompte = compteRepo.save(compte);
			transert.setCompte(compte);
			transert.setCreatedAt(new Date());
			transert.setUpdatedAt(new Date());
			opRepo.save(transert);
			return ResponseEntity.ok(updatedCompte);
		} else {
			return ResponseEntity.ok(compte);
		}
		
		}

}
