package com.sarki.micro.services;

import com.sarki.micro.model.Compte;
import com.sarki.micro.model.CompteMonnaie;
import com.sarki.micro.model.Transfert;
import com.sarki.micro.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class OperationsService {

    private final PlatformTransactionManager transactionManager;
    private final ClientRepository clientRepo;
    private final OperationRepository opRepo;
    private final CompteRepository compteRepo;
    private final CompteMonnaieRepository compteMonnaieRepo;
    private final TransfertRepository transfertRepo;

    public OperationsService(PlatformTransactionManager transactionManager, ClientRepository clientRepo, OperationRepository opRepo, CompteRepository compteRepo, CompteMonnaieRepository compteMonnaieRepo, TransfertRepository transfertRepo) {
        this.transactionManager = transactionManager;
        this.clientRepo = clientRepo;
        this.opRepo = opRepo;
        this.compteRepo = compteRepo;
        this.compteMonnaieRepo = compteMonnaieRepo;
        this.transfertRepo = transfertRepo;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Compte transfert(Compte expediteur, Compte beneficiaire, Transfert transert) {

        //Retirer de l'argent chez l'expediteur
        expediteur.setSolde(expediteur.getSolde() - transert.getMontant());
        if (expediteur instanceof CompteMonnaie)
            compteMonnaieRepo.save((CompteMonnaie) expediteur);

        //Crediter de l'argent chez le beneficiaire
        beneficiaire.setSolde(beneficiaire.getSolde() + transert.getMontant());
        if (expediteur instanceof CompteMonnaie)
            compteMonnaieRepo.save((CompteMonnaie) beneficiaire);

        //Sauvegarger l'historique de la transaction
        transert.setCompte(expediteur);
        transert.setCreatedAt(new Date());
        transert.setUpdatedAt(new Date());
        transfertRepo.save(transert);
        return expediteur;
    }
}
