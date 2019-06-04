package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("Epargne")
public class CompteEpargne extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319351353042327410L;
	
	private double taux;

	public CompteEpargne() {
		super();
	}

	public CompteEpargne(long id, @NotBlank String numCompte, double solde, Date createdAt, Date updatedAt) {
		super(id, numCompte, solde, createdAt, updatedAt);
	}

	public CompteEpargne(double taux) {
		super();
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	

}
