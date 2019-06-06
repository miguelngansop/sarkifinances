package com.sarki.micro.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="agences")
@EntityListeners(AuditingEntityListener.class)
public class Agence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7416218416954388846L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nomAgence;
	

	
	@OneToMany(mappedBy="agence",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Collection<Employe> employes;
	
	public Agence() {}

	public Agence(long id, @NotBlank String nomAgence) {
		super();
		this.id = id;
		this.nomAgence = nomAgence;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomAgence() {
		return nomAgence;
	}


	
	
	
	
	

}
