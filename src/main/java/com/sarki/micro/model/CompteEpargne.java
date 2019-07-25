package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Epargne")
public class CompteEpargne extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319351353042327410L;

	private double taux;
	private static double soldeMin = 5000, frais = 5000;

	public CompteEpargne() {
		super();
	}

	public CompteEpargne(long id, double solde, double emprunt, Date createdAt, Date updatedAt) {
		super(id, solde, emprunt, frais, createdAt, updatedAt);
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

	public static double getSoldeMin() {
		return soldeMin;
	}

	public static double getFrais() {
		return frais;
	}

}
