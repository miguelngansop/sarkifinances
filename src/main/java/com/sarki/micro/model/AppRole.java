package com.sarki.micro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppRole {
	@Id @GeneratedValue
	private Long id;
	private String role;
	
	
	
	public AppRole(Long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	public AppRole() {}

	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
