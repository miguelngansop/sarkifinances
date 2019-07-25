package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Monnaie")
public class CompteMonnaie extends Compte{

	public CompteMonnaie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteMonnaie(long id, double solde, Date createdAt, Date updatedAt) {
		super(id, solde, solde, solde, createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}

	public CompteMonnaie(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

}
