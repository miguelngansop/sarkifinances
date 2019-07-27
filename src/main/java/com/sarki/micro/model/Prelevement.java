package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Prelevement")
public class Prelevement extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5788146264427374598L;
	private long preleverSurMontant ;
	
	public Prelevement(long preleverSurMontant) {
		super();
		this.preleverSurMontant = preleverSurMontant;
	}
	public Prelevement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Prelevement(long id, double montant, Date createdAt, Date updatedAt) {
		super(id, montant, createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}
	
	public long getPreleverSurMontant() {
		return preleverSurMontant;
	}
	
	public void setPreleverSurMontant(long preleverSurMontant) {
		this.preleverSurMontant = preleverSurMontant;
	}

}
