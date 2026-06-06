package com.jpstechno.auth_ms.serviceImplementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.eventsManager.eventsPublisher.NewEcoleEvent;
import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.modeles.Ecoles;
import com.jpstechno.auth_ms.modeles.Fonctions;
import com.jpstechno.auth_ms.modeles.Dto.EcoleActeurDto;
import com.jpstechno.auth_ms.repositories.ActeurEcoleRepos;
import com.jpstechno.auth_ms.repositories.ActeurRepos;
import com.jpstechno.auth_ms.repositories.EcoleRepos;
import com.jpstechno.auth_ms.services.EcoleServ;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EcoleImplementation implements EcoleServ {

    private final EcoleRepos ecoleRepos;
    private final ActeurRepos acteurRepos;
    private final ActeurEcoleRepos acteurEcolerepos;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public Ecoles EnregistrerEcole(EcoleActeurDto ecoleDto) {

        // s'assurer qu'un promoteur est associe a la creation
        if (ecoleDto.getActeurs() == null) {
            throw new RuntimeException("Il doit y avoir un promoteur ajouter a la creation");
        }
        // recuperer l'acteur existant ou en creer un nouveau
        Acteurs acteur = acteurRepos.findByEmail(ecoleDto.getActeurs().getEmailPersonnel()).orElseGet(() -> {
            return acteurRepos.save(ecoleDto.getActeurs());
        });

        // Enregistrer l'ecole
        Ecoles newEcole = ecoleRepos.save(ecoleDto.getEcole());

        // Association des acteurs et des ecoles
        ActeurEcoles acteurEcole = new ActeurEcoles();
        acteurEcole.setActeur(acteur);
        acteurEcole.setEcole(newEcole);
        acteurEcole.setFonction(Fonctions.PROMOTEUR);
        acteurEcole.setActeurEcoleActif(false);
        acteurEcole.setDateDernierPassword(LocalDate.now());
        acteurEcole.setEmailInstitutionnel(ecoleDto.getEmailInstitutionnel());
        acteurEcole.setPassword(ecoleDto.getPassword());
        acteurEcolerepos.save(acteurEcole);

        // Publier evenement de creation de nouvelle ecole
        NewEcoleEvent newEcoleEvent = new NewEcoleEvent(newEcole);
        publisher.publishEvent(newEcoleEvent);
        return newEcole;
    }

    @Override
    public Ecoles modifierEcole(Long id, Ecoles ecole) {
        return null;
    }

    @Override
    public String activerOuDesactiverEcole(long id) {
        Ecoles ecole = ecoleRepos.findById(id).orElse(null);
        if (ecole == null) {
            throw new RuntimeException("Cette ecole n'existe pas");
        }
        ecole.setEcoleActif(!ecole.isEcoleActif());

        ecoleRepos.save(ecole);

        return ecole.isEcoleActif() ? "Ecole activee avec succes" : "Ecole desactive avec succes";

    }

    @Override
    public List<Ecoles> rechercherEcolesParNomOuId(String idOuDenomination) {
        try {
            List<Ecoles> liste = new ArrayList<Ecoles>();
            long id = Long.parseLong(idOuDenomination);
            Ecoles ecole = ecoleRepos.findById(id).orElseThrow();
            liste.add(ecole);
            return liste;
        } catch (NumberFormatException nex) {
            return ecoleRepos.rechercheParDenomination(idOuDenomination);
        } catch (NoSuchElementException eltExcept) {
            throw new RuntimeException("Pas d'ecole avec cet id ou denomination");
        }

    }

    @Override
    public void supprimerEcole(Long id) {

    }

    @Override
    public List<Ecoles> getAllEcoles() {
        Sort trieAscParDenomination = Sort.by(Direction.DESC, "nomEcole");
        return ecoleRepos.findAll(trieAscParDenomination);
    }

}
