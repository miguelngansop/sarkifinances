package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tontine")
public class CompteTontine extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319351353042327410L;

	private double tauxPreleve;

	public CompteTontine() {
		super();
	}

	public CompteTontine(long id, double solde, double emprunt, double frais, Date createdAt, Date updatedAt) {
		super(id, solde, emprunt, frais, createdAt, updatedAt);
	}

	public CompteTontine(double tauxPreleve) {
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
