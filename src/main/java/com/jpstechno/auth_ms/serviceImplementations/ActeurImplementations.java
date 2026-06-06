package com.jpstechno.auth_ms.serviceImplementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.repositories.ActeurRepos;
import com.jpstechno.auth_ms.services.ActeurServ;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActeurImplementations implements ActeurServ {

    private final ActeurRepos acteurRepos;

    @Override
    public Acteurs enregistrer(Acteurs acteur) {
        // rechercher si acteur ayant email existait
        boolean acteurMailExisted = acteurRepos.findByEmail(acteur.getEmailPersonnel()).isPresent();
        if (!acteurMailExisted) { // si pas acteur avec same email
            return acteurRepos.save(acteur);
        } else {
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

}
