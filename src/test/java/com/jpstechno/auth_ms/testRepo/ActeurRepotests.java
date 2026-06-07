/**
 * Test pour l'enregistrement des donnees dans la base de donnees pour les acteurs
 */

package com.jpstechno.auth_ms.testRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.modeles.Sexes;
import com.jpstechno.auth_ms.repositories.ActeurRepos;

@DataJpaTest
@DisplayName("acteurs Data test")
@TestMethodOrder(MethodOrderer.class)
public class ActeurRepotests {

    @Autowired
    private ActeurRepos acteurRepo;

    @Test
    @DisplayName("test enregistrement nouveau acteur")
    @Order(1)
    @Rollback(false)
    void enregistrerNewActeur() {
        // Creation de la donnees Acteur a enregistrer
        Acteurs act = new Acteurs();
        act.setNom("Sovi");
        act.setPrenom("Jean");
        act.setSexe(Sexes.MASCULIN);
        act.setEmailPersonnel("jeansovi@easyschool.edu");
        act.setTelephone("514");

        // Enregistrement de l'acteur
        Acteurs savedActeur = acteurRepo.save(act);

        // les assertions
        assertNotNull(savedActeur);
        assertFalse(savedActeur.isActif());
        assertFalse(savedActeur.isEmailVerified());
        assertEquals(1, savedActeur.getActeurId());

    }

}
