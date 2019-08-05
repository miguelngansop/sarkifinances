package com.sarki.micro.controller;

import com.sarki.micro.model.*;
import com.sarki.micro.repository.*;
import com.sarki.micro.services.OperationsService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/monnaie")
public class OperationsMonnaieCtrl {

    final
	CompteRepository compteRepo;
    final
	ClientRepository clientRepo;
    final
	OperationRepository opRepo;

    final
    AgentRepository agentRepo;

    private final OperationsService operationsService;
    private final CompteMonnaieRepository compteMonnaieRepo;
    private final TransfertRepository transfertRepo;

	@Autowired
    public OperationsMonnaieCtrl(CompteRepository compteRepo, ClientRepository clientRepo, OperationRepository opRepo, AgentRepository agentRepo, OperationsService operationsService, CompteMonnaieRepository compteMonnaieRepository, TransfertRepository transfertRepo) {
        this.compteRepo = compteRepo;
        this.clientRepo = clientRepo;
        this.opRepo = opRepo;
        this.agentRepo = agentRepo;
        this.operationsService = operationsService;
        this.compteMonnaieRepo = compteMonnaieRepository;
        this.transfertRepo = transfertRepo;
    }
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

        Compte expediteur = compteRepo.findById(idExpediteur)
                .orElseThrow(() -> new ResourceNotFoundException("Compte expiditaire", "id", idExpediteur));

        Compte beneficaire = compteRepo.findById(idBeneficiaire)
                .orElseThrow(() -> new ResourceNotFoundException("Compte beneficiaire", "id", idBeneficiaire));

        if (expediteur.getSolde() >= transert.getMontant()) {
            return ResponseEntity.ok(operationsService.transfert(expediteur, beneficaire, transert));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solde Expediteur Insuffisant");
        }

    }

    //Historique des transactions
    @GetMapping("/transactions/{idExpediteur}")
    public List<Operation> getHistoriqueTransactions(@PathVariable(value = "idExpediteur") Long idExpediteur) {
        return opRepo.findAllByCompteIdOrderByCreatedAtDesc(idExpediteur);
    }
}
