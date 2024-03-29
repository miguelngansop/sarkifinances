package com.sarki.micro.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_DE_CLIENT", discriminatorType = DiscriminatorType.STRING, length = 25)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Particulier.class, name = "particulier"),
		@Type(value = Commercants.class, name = "commercant") })
public class Client  {

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private String numcni;
	private String telephone;
	private String email;
	private String residence;
	private String profession;
	private String nomDuPere;
	private String nomDeLaMere;
	private String photo;

	@JsonBackReference("comptes-client")
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Compte> comptes;
	
	
	 @OneToOne
	 @JoinColumn(name = "user_id", referencedColumnName = "id")
	 private AppUser appuser;

	public Client() {
	}

	public Client(long id, @NotBlank String nom,  String prenom, Date dateDeNaissance,  String numcni,
			String telephone, String email, String residence, String profession, String nomDuPere, String nomDeLaMere,
			String photo) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.numcni = numcni;
		this.telephone = telephone;
		this.email = email;
		this.residence = residence;
		this.profession = profession;
		this.nomDuPere = nomDuPere;
		this.nomDeLaMere = nomDeLaMere;
		this.photo = photo;
	}

	public Client(int i) {
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getNomDuPere() {
		return nomDuPere;
	}

	public void setNomDuPere(String nomDuPere) {
		this.nomDuPere = nomDuPere;
	}

	public String getNomDeLaMere() {
		return nomDeLaMere;
	}

	public void setNomDeLaMere(String nomDeLaMere) {
		this.nomDeLaMere = nomDeLaMere;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}
	
	

}
