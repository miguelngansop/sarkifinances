package com.sarki.micro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Employes")
@EntityListeners(AuditingEntityListener.class)
public class Employe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5058874772657972431L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	private Date dateDeNaissance;
	private String numcni;
	private String telephone;
	
	
	public Employe() {}

	public Employe(long id, @NotBlank String nom, @NotBlank String prenom, Date dateDeNaissance, String numcni,
			String telephone) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.numcni = numcni;
		this.telephone = telephone;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getNumcni() {
		return numcni;
	}

	public void setNumcni(String numcni) {
		this.numcni = numcni;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	}
	

