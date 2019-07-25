package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Investissement")
public class CompteInvestissement extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319351353042327410L;

	private double tauxPreleve;

	public CompteInvestissement() {
		super();
	}

	public CompteInvestissement(long id, double solde, double frais, double emprunt, Date createdAt, Date updatedAt) {
		super(id, solde, emprunt, frais, createdAt, updatedAt);
	}

	public CompteInvestissement(double tauxPreleve) {
		super();
		this.tauxPreleve = tauxPreleve;
	}

	public double getTauxPreleve() {
		return tauxPreleve;
	}

	public void setTauxPreleve(double tauxPreleve) {
		this.tauxPreleve = tauxPreleve;
	}

}
