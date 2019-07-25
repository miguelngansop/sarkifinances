package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Mutuelle")
public class CompteMutuelle extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319351353042327410L;

	private double tauxPreleve;

	public CompteMutuelle() {
		super();
	}

	

	public CompteMutuelle(long id, double solde, Date createdAt, Date updatedAt) {
		super(id, solde, createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}



	public CompteMutuelle(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}



	public CompteMutuelle(double tauxPreleve) {
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
