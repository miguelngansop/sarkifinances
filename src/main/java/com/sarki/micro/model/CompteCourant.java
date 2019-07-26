package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Courant")
public class CompteCourant extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1749158438375635909L;

	private double decouvert;

	public CompteCourant() {
		super();
	}

	public CompteCourant(double decouvert) {
		super();
		this.decouvert = decouvert;
	}

	public CompteCourant(long id, double solde, double emprunt, double frais, Date createdAt, Date updatedAt) {
		super(id, solde, emprunt, frais, createdAt, updatedAt);
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

}
