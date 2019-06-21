package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Islamique")
public class CompteIslamique extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319351353042327410L;

	private double tauxPreleve;

	public CompteIslamique() {
		super();
	}

	public CompteIslamique(long id, double solde, double emprunt, double frais, Date createdAt, Date updatedAt) {
		super(id, solde, emprunt, frais, createdAt, updatedAt);
	}

	public CompteIslamique(double tauxPreleve) {
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
