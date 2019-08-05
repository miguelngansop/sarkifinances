
package com.sarki.micro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "comptes")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_DE_COMPTE", discriminatorType = DiscriminatorType.STRING, length = 15)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = CompteMonnaie.class, name = "monnaie") })
public abstract class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3862587587729262078L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double solde;
	private double emprunt;
	// private double frais;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "CODE_CLI")
	private Client client;

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

	@JsonBackReference("operations-compte")
	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;

	public Compte() {
	}

	public Compte(long id) {
		super();
		this.id = id;
	}

	public Compte(long id, double solde, double emprunt, double frais, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.solde = solde;
		this.emprunt = emprunt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public double getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(double emprunt) {
		this.emprunt = emprunt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Client getClient() {
		return client;
	}
}