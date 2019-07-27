package com.sarki.micro.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Agent  implements Serializable{

	private static final long serialVersionUID = -7058577205283949035L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String noms;
	private String cni;
	private String telephone;
	
	@ManyToOne
	@JoinColumn(name = "CODE_AGENCE")
	private Agence  agence;
	
	@OneToMany(mappedBy="agent",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Collection<Operation> operations;
	
	public Agent(Long id, String noms, String cni, String telephone) {
		super();
		this.id = id;
		this.noms = noms;
		this.cni = cni;
		this.telephone = telephone;
	}

	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoms() {
		return noms;
	}

	public void setNoms(String noms) {
		this.noms = noms;
	}

	public String getCni() {
		return cni;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/*public Agence getAgence() {
		return agence;
	}*/

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*public Collection<Operation> getOperations() {
		return operations;
	}*/

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

	

	

}
