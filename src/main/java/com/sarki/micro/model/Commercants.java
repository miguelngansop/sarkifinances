package com.sarki.micro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("commercant")
public class Commercants extends Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5872876714516006240L;

	public Commercants() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commercants(long id, @NotBlank String nom, @NotBlank String prenom, Date dateDeNaissance,
			@NotBlank String numcni, String telephone, String email, String residence, String profession,
			String nomDuPere, String nomDeLaMere, String photo) {
		super(id, nom, prenom, dateDeNaissance, numcni, telephone, email, residence, profession, nomDuPere, nomDeLaMere, photo);
		// TODO Auto-generated constructor stub
	}
	

	
}
