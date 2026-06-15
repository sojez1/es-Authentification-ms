package com.jpstechno.auth_ms.controlleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.services.ActeurEcoleServ;

import lombok.RequiredArgsConstructor;

/**
 * Contrôleur pour gérer les opérations liées aux écoles
 * (ex: création, mise à jour, suppression, etc.)
 */

@RestController
@RequestMapping("/ecoles")
@RequiredArgsConstructor
public class EcoleControl {

    private final ActeurEcoleServ acteurEcoleServ;

    @GetMapping("/newEcole/confirmation/acteur")
    public String confirmationActeur() {

        return null;

    }

    @PostMapping("/utilisateurs/ajouter")
    public ActeurEcoles ajouterUtilisateur() {
        return null;
    }

}
