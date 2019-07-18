package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Collecte")
public class CompteCollecte extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319351353042327410L;
	
	private static double tauxPreleve = 3;

	public CompteCollecte() {
		super();
	}

	
	public CompteCollecte(long id, double solde, Date createdAt, Date updatedAt) {
		super(id, solde, createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}


	public CompteCollecte(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	
	public double getTauxPreleve() {
		return tauxPreleve;
	}


}