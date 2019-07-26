package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Versement")
public class Versement extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -626648393813600963L;

	public Versement() {
		super();
	}

	public Versement(long id, double montant, Date createdAt, Date updatedAt) {
		super(id, montant, createdAt, updatedAt);
	}
	
	

}
