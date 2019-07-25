package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Emprunt")
public class Emprunt extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4988618213487184480L;
	private Date dateEcheance;
	private int nbreTranche;

	public Emprunt() {
		super();
	}

	public Emprunt(long id, double montant, Date createdAt, Date updatedAt) {
		super(id, montant, createdAt, updatedAt);
	}

	public Emprunt(Date dateEcheance, int nbreTranche) {
		super();
		this.dateEcheance = dateEcheance;
		this.nbreTranche = nbreTranche;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public int getNbreTranche() {
		return nbreTranche;
	}

	public void setNbreTranche(int nbreTranche) {
		this.nbreTranche = nbreTranche;
	}

}
