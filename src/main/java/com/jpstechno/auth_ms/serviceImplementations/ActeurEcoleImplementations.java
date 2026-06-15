package com.jpstechno.auth_ms.serviceImplementations;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.modeles.Ecoles;
import com.jpstechno.auth_ms.repositories.ActeurEcoleRepos;
import com.jpstechno.auth_ms.repositories.EcoleRepos;
import com.jpstechno.auth_ms.services.ActeurEcoleServ;
import com.jpstechno.auth_ms.services.ActeurServ;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActeurEcoleImplementations implements ActeurEcoleServ {

    private final ActeurServ acteurServ;
    private final EcoleRepos ecoleRepo;
    private final ActeurEcoleRepos acteurEcoleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ActeurEcoles saveUserForSchool(ActeurEcoles acteurEcole) {

        // 1- verifier si acteur existe deja ou non
        Acteurs acteur = acteurServ.rechercherActeurParEmail(acteurEcole.getActeur().getEmailPersonnel()).orElse(null);
        if (acteur == null) {
            acteur = acteurServ.enregistrer(acteur);
        }

        // 2- Verifier si ecole existe deja ou non
        Ecoles ecole = ecoleRepo.findById(null).orElse(null);

        // 3- Crypter le mot de passe
        String encryptedPassword = passwordEncoder.encode(acteurEcole.getPassword());
        acteurEcole.setPassword(encryptedPassword);

        // 4- Save to database
        acteurEcole.setActeur(acteur);
        acteurEcole.setEcole(ecole);
        ActeurEcoles ae = acteurEcoleRepo.save(acteurEcole);

        // Do not return ae with the user password, so empty it before
        ae.setPassword("");
        return ae;

    }

    @Override
    public String modifierPassword(String email, long school_id, String oldPassword, String newPassord) {
        return null;
    }

    @Override
    public String desactiverUtilisateur(long id) {
        return null;
    }

    @Override
    public String ajouterPromoteur(Acteurs promoteur, long school_id) {
        return null;
    }

    @Override
    public String retirerPromoteur(long promoteur_id, long school_id) {
        return null;
    }

}
