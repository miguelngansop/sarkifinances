package com.sarki.micro.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.Client;
import com.sarki.micro.model.Compte;
import com.sarki.micro.model.CompteCourant;
import com.sarki.micro.model.CompteEpargne;
import com.sarki.micro.model.Operation;
import com.sarki.micro.model.Versement;
import com.sarki.micro.repository.ClientRepository;
import com.sarki.micro.repository.CompteRepository;
import com.sarki.micro.repository.OperationRepository;

import exception.ResourceNotFoundException;

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
	private Compte c;
	
	  @PostMapping("/comptecourant/{id}")
	  public CompteCourant createCompteCourant( @PathVariable(value = "id") Long clientId ) {
		  CompteCourant cpt = new CompteCourant();
		  if(clientRepo.existsById(clientId)) {
				  cpt.setSolde(0); // initialisation du solde a la creation
				  cpt.setCreatedAt(new Date());
				  cpt.setDecouvert(0); // initialisation du decouvert a la creation
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
				  
				  return compteRepo.save(cpt);
		  } else {
			  return null;
		  }
		  
	      
	  }
	  
	// Create a new CompteEpargne
	  
		  @PostMapping("/compteEpargne/{id}")
		  public CompteEpargne createCompteEpargne( @PathVariable(value = "id") Long clientId ) {
			  CompteEpargne cpt = new CompteEpargne();
			  if (clientRepo.existsById(clientId)) {
				  cpt.setSolde(0); // initialisation du solde a la creation
				  cpt.setCreatedAt(new Date());
				  cpt.setTaux(5);
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
				  
			      return compteRepo.save(cpt);
			  }else {
				  return null;
			  }
		  }
	 
	// Get All Comptes
		  @GetMapping("/comptes")
		  public List<Compte> getAllCompte() {
		      return compteRepo.findAll();
		  }
	
	// Get a Single Compte
	  @GetMapping("/compte/{id}")
	  public Compte getCompteById(@PathVariable(value = "id") Long compteId) {
	      return compteRepo.findById(compteId)
	              .orElseThrow(() -> new ResourceNotFoundException("Compte", "id", compteId));
	  }
	  
	  
	  
	  @PatchMapping("/compte/{id}")
	    public ResponseEntity<Compte> versement(@PathVariable(value = "id") Long compteId,
	         @Valid @RequestBody Versement details) throws ResourceNotFoundException {
	        Compte compte =  compteRepo.findById(compteId)
	        .orElseThrow(() -> new ResourceNotFoundException("CompteCourant", "id", compteId));
	        compte.setSolde(compte.getSolde() + details.getMontant());
	        final Compte updatedCompte = compteRepo.save(compte);
	        details.setCompte(compte);
	        details.setCreatedAt(new Date());
	        details.setUpdatedAt(new Date());
	        opRepo.save(details);
	        return ResponseEntity.ok(updatedCompte);
	    }
	  
	  
	// Operation de versement
	  /*
	  @PostMapping("/operationDeVersement")
	  public CompteEpargne createCompteEpargne(@Valid @RequestBody Versement opVersement ) {
		
		  
		  
		  if (clientRepo.existsById(clientId)) {
			  cpt.setSolde(0); // initialisation du solde a la creation
			  cpt.setCreatedAt(new Date());
			  cpt.setTaux(5);
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
			  
		      return compteRepo.save(cpt);
		  }else {
			  return null;
		  }
	  }
	  */
	  
	  /*
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
