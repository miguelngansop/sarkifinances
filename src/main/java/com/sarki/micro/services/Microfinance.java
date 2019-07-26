package com.sarki.micro.services;

import java.util.List;

import com.sarki.micro.interfaces.IMicrofinance;
import com.sarki.micro.model.Client;
import com.sarki.micro.model.Compte;
import com.sarki.micro.model.Employe;
import com.sarki.micro.model.Operation;
import com.sarki.micro.repository.EmployeRepository;

public class Microfinance implements IMicrofinance {


	@SuppressWarnings("null")
	@Override
	public void addEmploye(Employe e) {
		// TODO Auto-generated method stub
		EmployeRepository er = null ;
		er.save(e);
		
	}

	@Override
	public void addEmployeToAgence(Long idAgence, Long idEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClient(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCompte(Compte c, Long numCli, Long numEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOperation(Operation op, String numCpte, Long numEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte consulterCompte(String numCpte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> consulterClientsParNom(String mc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> consulterClients() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Employe> consulterEmployes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employe> consulterEmployesParAgence(Long idG) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employe consulterEmploye(Long idEmp) {
		// TODO Auto-generated method stub
		return null;
	}

}
