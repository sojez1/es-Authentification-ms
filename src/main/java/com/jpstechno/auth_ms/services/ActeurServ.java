package com.jpstechno.auth_ms.services;

import java.util.List;
import java.util.Optional;

import com.jpstechno.auth_ms.modeles.Acteurs;

public interface ActeurServ {

    Acteurs enregistrer(Acteurs acteur);

    String UpdateActeur(long acteurId, Acteurs newDetails);

    String ActiverOuDesactiverActeur(String IdOrEmail);

    Optional<Acteurs> rechercherActeurParEmail(String email);

    List<Acteurs> rechercherParMotCle(String motCle);
}
