package com.jpstechno.auth_ms.controlleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Contrôleur pour gérer les opérations liées aux écoles
 * (ex: création, mise à jour, suppression, etc.)
 */

@RestController
@RequestMapping("/ecoles")
@RequiredArgsConstructor
public class EcoleControl {

    @GetMapping("/newEcole/confirmation/acteur")
    public void confirmationActeur() {

    }

}
