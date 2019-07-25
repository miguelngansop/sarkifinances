package com.sarki.micro.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bloque")
public class CompteBloque extends Compte {

	/**
	 * 
	 */
	

	private static double taux=3/2;
	private static Long fraisDeCreation = (long) 0;
	private static Long soldeMin = (long) 10000;
	
	private static final long serialVersionUID = 319351353042327410L;

	public CompteBloque() {
		super();
	}

	public CompteBloque(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public static Long getFraisDeCreation() {
		return fraisDeCreation;
	}

	public static void setFraisDeCreation(Long fraisDeCreation) {
		CompteBloque.fraisDeCreation = fraisDeCreation;
	}

	public static Long getSoldeMin() {
		return soldeMin;
	}

	public static void setSoldeMin(Long soldeMin) {
		CompteBloque.soldeMin = soldeMin;
	}



}