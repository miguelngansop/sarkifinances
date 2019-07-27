package com.sarki.micro.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agence implements Serializable {

	private static final long serialVersionUID = -7416218416954388846L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	
	@OneToMany(mappedBy="agence",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Collection<Agent> agents;
	public Agence(Long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	public Agence() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/*public Collection<Agent> getAgents() {
		return agents;
	}*/
	public void setAgents(Collection<Agent> agents) {
		this.agents = agents;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
