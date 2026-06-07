package com.jpstechno.auth_ms.serviceImplementations;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.eventsManager.eventsPublisher.NewActeurEvent;
import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.repositories.ActeurRepos;
import com.jpstechno.auth_ms.repositories.UrlVerificationRepos;
import com.jpstechno.auth_ms.services.ActeurServ;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActeurImplementations implements ActeurServ {

    private final ActeurRepos acteurRepos;
    private final UrlVerificationRepos urlverif;
    private ApplicationEventPublisher publisher;
    private Logger log = LoggerFactory.getLogger(Acteurs.class);

    @Override
    public Acteurs enregistrer(Acteurs acteur) {
        // rechercher si acteur ayant email existait
        boolean acteurMailExisted = acteurRepos.findByEmail(acteur.getEmailPersonnel()).isPresent();
        if (!acteurMailExisted) { // si pas acteur avec same email
            acteur.setActif(true);
            acteur.setEmailVerified(false);
            Acteurs act = acteurRepos.save(acteur);

            // enregistrer le log
            log.info("Enregistrement d'un nouveau acteur", act);

            // publier evenement en vue confirmation email
            NewActeurEvent acteurEvent = new NewActeurEvent(act);
            publisher.publishEvent(acteurEvent);

            return act;
        } else {
            log.error("Echec creation d'un nouvel acteur", acteur);
            throw new RuntimeException("Cette adresse email existe deja");

        }
    }

    @Override
    public String UpdateActeur(long acteurId, Acteurs newDetails) {
        Optional<Acteurs> acteurExisted = acteurRepos.findById(acteurId);
        if (acteurExisted.isPresent()) {
            Acteurs toBeModified = acteurExisted.get();

            toBeModified.setNom(newDetails.getNom());
            toBeModified.setPrenom(newDetails.getPrenom());
            toBeModified.setSexe(newDetails.getSexe());

            acteurRepos.save(toBeModified);
            return String.format("Les donnees de l'utilisateur %s %s sont modifiees avec succees",
                    newDetails.getPrenom(), newDetails.getNom());

        } else {
            throw new RuntimeException("L'utilisateur que vous tentez de modifier n'existe pas");
        }
    }

    @Override
    public Optional<Acteurs> rechercherActeurParEmail(String email) {
        return acteurRepos.findByEmail(email);
    }

    @Override
    public List<Acteurs> rechercherParMotCle(String motCle) {
        return acteurRepos.rechercherParMotCle(motCle);
    }

    @Override
    public String ActiverOuDesactiverActeur(String idOrEmail) {

        Acteurs act;

        try {
            long id = Long.parseLong(idOrEmail);
            act = acteurRepos.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            act = acteurRepos.findByEmail(idOrEmail).orElse(null);
        }

        if (act == null) {
            return "Aucun utilisateur avec cet identifiant ou email";
        }

        act.setActif(!act.isActif()); // si actif passer inactif ou vice-versa
        acteurRepos.save(act);

        return act.isActif() ? "Utilisateur active avec succes" : "Utilisateur desactive avec succes";
    }

    @Override
    public String confirmationEmail(long id, String token) {
        // verification du token
        boolean tokenValide = urlverif.rechercherParId_Token(id, token).isPresent();

        if (tokenValide) {
            Acteurs act = acteurRepos.findById(id).orElse(null);
            if (act == null) {
                throw new RuntimeException("Utilisateur non trouve");
            }

            act.setEmailVerified(true);
            acteurRepos.save(act);
            return "validation email reussie";

        } else {
            throw new RuntimeException("Lien invalide");
        }
    }

    @Override
    public List<Acteurs> listeActeurParEcole(long school_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listeActeurParEcole'");
    }

    @Override
    public Acteurs rechercherActeurParId(long id) {
        return acteurRepos.findById(id).orElse(null);
    }

}
