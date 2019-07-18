package com.sarki.micro.controller;


import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.sarki.micro.model.CompteCollecte;
import com.sarki.micro.model.Operation;
import com.sarki.micro.model.Prelevement;
import com.sarki.micro.model.Retrait;
import com.sarki.micro.model.Versement;
import com.sarki.micro.repository.ClientRepository;
import com.sarki.micro.repository.CompteCollecteRepository;
import com.sarki.micro.repository.CompteRepository;
import com.sarki.micro.repository.OperationRepository;

import exception.ResourceNotFoundException;

@RestController
@RequestMapping("/apiCompteCollecte")
public class CompteCollecteController {

	
	@Autowired
	CompteRepository compteRepo;
	
	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	CompteCollecteRepository compteCollecteRepo;
	
	@Autowired
	OperationRepository opRepo;
	// Create a new CompteCollecte
	
	
	// Get All Comptes
			@GetMapping("/comptes")
			public List<CompteCollecte> getAllCompte() {
				return compteCollecteRepo.findAll();
			}
	

	@PostMapping("/compteCollecte/{id}")
	public CompteCollecte createCompteCollecte( @PathVariable(value = "id") Long clientId,
			@Valid @RequestBody Long montant ) {
		
	if(montant >= 0) {
		CompteCollecte cpt = new CompteCollecte();
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
			CompteCollecte cptC = new CompteCollecte();
			cpt.setSolde(montant);				 // initialisation du solde a la creation
			cptC = compteRepo.save(cpt);		// recuperation du compte cree
												// versement du reste dans le compte
			v.setCompte(cptC);
			v.setCreatedAt(new Date());
			v.setUpdatedAt(new Date());
			v.setMontant(montant);
			v.setSoldePrecedent(0);
			opRepo.save(v);						 // operation valide
			
			return cptC;
		}else {
			return null;
		}
	}else {return null;}
	}

	//retrait Collecte
	@PatchMapping("/compte/retrait/{id}")
	public ResponseEntity<?> retrait(@PathVariable(value = "id") Long compteId,
			@Valid @RequestBody Retrait details) throws ResourceNotFoundException {
		CompteCollecte compte =  (CompteCollecte) compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("CompteCollecte", "id", compteId));
		if( compte.getSolde() >= details.getMontant()  ) {
			details.setSoldePrecedent(compte.getSolde());
			compte.setSolde(compte.getSolde() - details.getMontant());
			final Compte updatedCompte = compteRepo.save(compte);
			details.setCompte(compte);
			details.setCreatedAt(new Date());
			details.setUpdatedAt(new Date());
			//conversion de signe
			details.setMontant(details.getMontant());
			opRepo.save(details);
			return ResponseEntity.ok(updatedCompte);
			
		}else {
			return ResponseEntity.ok(compte);
		}

	}
	
	
	//prelever les frais de commissions
	@PatchMapping("/compte/commission/{id}")
	public ResponseEntity<?> commissions(@PathVariable(value = "id") Long compteId) throws ResourceNotFoundException {
		// lecture du compte
		CompteCollecte compte =  (CompteCollecte) compteRepo.findById(compteId)
				.orElseThrow(() -> new ResourceNotFoundException("CompteCollecte", "id", compteId));
		
		//montant sur le quel sera applique le prelevement 
		long montantAp = 0 ; 
		//liste des operations du compte
		List<Operation> listOperations = (List<Operation>) compte.getOperations();
		//recherchons la liste des derniers versements
		//ArrayList<Operation> listVersementRecents;
		 // commencons par renverser la liste
		
		int indexDerniereOperation = listOperations.size() - 1;
		int index = indexDerniereOperation;
		Boolean founded = false;
		while(index >= 0 && !founded) {
			if(listOperations.get(index).getMontant()< 0 || listOperations.get(index) instanceof Retrait ) // si c'est un retrait (-)
			{
				founded = true;
			}
			index = index - 1 ;
		}
		//reuperation des derniers versements
		
		index = founded ? index + 1 : 0 ; // position exacte du dernier retrait si on trouve 
		
		int taille = indexDerniereOperation + 1;
		
		while(indexDerniereOperation > index || ( (taille == 1) && (listOperations.get(index) instanceof Versement ) ) ) {
			taille = 0 ; // fin pour le cas d'un element
			//listVersementRecents.add(listOperations.get(indexDerniereOperation)); //liste des versements recents
			// prnons compte des montant ayant fait plus d'un mois
			Date now = new Date();
			Date from = listOperations.get(indexDerniereOperation).getCreatedAt();
			
			if(listOperations.get(indexDerniereOperation) instanceof Versement) {
			long days = TimeUnit.DAYS.convert((now.getTime() - from.getTime()), TimeUnit.MILLISECONDS); // nombre de jours
			// a supposer que nous prelevons par jour juste pour les testes nous avons : 
			days = days > 1 ? days : 1;  // le service est prepaye
			System.out.println("\n Le nombre de jours est de : " + days);
			long auxMontant = (long) listOperations.get(indexDerniereOperation).getMontant() ;
			long auxDays = days;
			while( auxDays >  0 ) {
			montantAp = montantAp + auxMontant;
			auxMontant = (long) (auxMontant - compte.getTauxPreleve()*auxMontant/100) ;
			auxDays = auxDays - 1 ;  
			}
			//montantAp = (long) (montantAp + days*listOperations.get(indexDerniereOperation).getMontant());
			}
			indexDerniereOperation = indexDerniereOperation - 1 ;
		}
		
		// nous avons au sorti de cette boucle montant sur le quel nous devons prelever les frais d'entretient
		//initialisation du prelevement
		Prelevement p = new Prelevement(montantAp);
		p.setMontant(montantAp*compte.getTauxPreleve()/100);
		p.setCreatedAt(new Date());
		p.setUpdatedAt(new Date());
		p.setSoldePrecedent(compte.getSolde());
		
		if( compte.getSolde() >= p.getMontant() &&  p.getMontant()>0  ) { // a revoir en cas de credit / bref questionner les amis
			compte.setSolde(compte.getSolde() - p.getMontant());   // on preleve que lorsque le montant a prelever est positif
			p.setCompte(compte);
			final Prelevement prelev =	opRepo.save(p); //enregistrement du prelevement
			compteRepo.save(compte);
			return ResponseEntity.ok(prelev);
			  
		}else {
			return ResponseEntity.ok(p);
		}

	}
	
	
	


}
