package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Remboursement")
public class Remboursement extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4988618213487184480L;

	public Remboursement() {
		super();
	}

	public Remboursement(long id, double montant, Date createdAt, Date updatedAt) {
		super(id, montant, createdAt, updatedAt);
	}
	
	
	

}
