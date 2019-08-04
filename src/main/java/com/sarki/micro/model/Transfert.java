package com.sarki.micro.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("transfert")
public class Transfert extends Operation {

	private static final long serialVersionUID = 5346137113635275473L;

	public Transfert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transfert(long id, double montant, Date createdAt, Date updatedAt) {
		super(id, montant, createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}
	

}
