package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Epargne")
public class CompteEpargne extends Compte {

	/**
	 * 
	 *
	 */
	private static final long serialVersionUID = 319351353042327410L;
	
	private static double taux=3;
	private static Long fraisDeCreation = (long) 5000;
	private static Long soldeMin = (long) 5000;

	public CompteEpargne() {
		super();
	}

	public CompteEpargne(long id,  double solde, Date createdAt, Date updatedAt) {
		super(id, solde, createdAt, updatedAt);
	}

	public CompteEpargne(double taux) {
		super();
		CompteEpargne.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		CompteEpargne.taux = taux;
	}

	public static Long getFraisDeCreation() {
		return fraisDeCreation;
	}

	public static void setFraisDeCreation(Long fraisDeCreation) {
		CompteEpargne.fraisDeCreation = fraisDeCreation;
	}

	public static Long getSoldeMin() {
		return soldeMin;
	}

	public static void setSoldeMin(Long soldeMin) {
		CompteEpargne.soldeMin = soldeMin;
	}
	
	

}
