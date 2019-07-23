package com.sarki.micro.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Monnaie")
public class CompteMonnaie extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
