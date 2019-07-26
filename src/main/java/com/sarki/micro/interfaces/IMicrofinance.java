package com.sarki.micro.interfaces;

import java.util.List;

import com.sarki.micro.model.Client;
import com.sarki.micro.model.Compte;
import com.sarki.micro.model.Employe;
import com.sarki.micro.model.Operation;

public interface IMicrofinance {
	
	public void addEmploye(Employe e);
	public void addEmployeToAgence(Long idAgence,Long idEmp);
	public void addClient(Client c);
	public void addCompte(Compte c,Long numCli,Long numEmp);
	public void addOperation(Operation op,String numCpte,Long numEmp);
	public Compte consulterCompte(String numCpte);
	public List<Client> consulterClientsParNom(String mc);
	public List<Client> consulterClients();
	public List<Employe> consulterEmployes();
	public List<Employe> consulterEmployesParAgence(Long idG);
	public Employe consulterEmploye(Long idEmp);

}
