package com.sarki.micro.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("Commercants")
public class Commercants extends Client {

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
