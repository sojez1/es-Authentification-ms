package com.jpstechno.auth_ms.serviceImplementations;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.modeles.Coursier;
import com.jpstechno.auth_ms.modeles.UrlVerification;
import com.jpstechno.auth_ms.repositories.UrlVerificationRepos;
import com.jpstechno.auth_ms.services.UrlVerificationServ;
import com.jpstechno.auth_ms.utilitaires.ConstanteParams;
import com.jpstechno.auth_ms.utilitaires.MessageTemplates;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlVerificationImplementation implements UrlVerificationServ {

    private final Coursier coursier;
    private final UrlVerificationRepos urlVerificationRepos;

    @Override
    public String generateVerificationUrl(Acteurs acteur, String chemin) {

        // 1. Generer le token de verification
        String verificationToken = generateVerificationToken();

        // 2. Creer url de verification
        String verificationUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(chemin)
                .queryParam("id", acteur.getActeurId())
                .queryParam("token", verificationToken)
                .toUriString();

        // Enregistrer ou Mettre a jour le token de validation dans la base de donnees
        UrlVerification urlVerification = urlVerificationRepos.findById(acteur.getActeurId()).orElse(null);

        if (urlVerification == null) { // Si aucune URL de vérification n'existe pour cet acteur, en créer une
                                       // nouvelle

            urlVerification = new UrlVerification();
            urlVerification.setActeurEcole(acteur);
            urlVerification.setVerificationToken(verificationToken);
            urlVerification.setUsed(false);
            urlVerification
                    .setExpirationTime(
                            System.currentTimeMillis() + ConstanteParams.dureeValiditeMessage * 60 * 1000);

        } else { // Sinon, mettre à jour l'URL de vérification existante
            urlVerification.setVerificationToken(verificationToken);
            urlVerification.setUsed(false);
            urlVerification
                    .setExpirationTime(
                            System.currentTimeMillis() + ConstanteParams.dureeValiditeMessage * 60 * 1000); // minutes
        }

        urlVerificationRepos.save(urlVerification);

        return verificationUrl;

    }

    @Override
    public void sendVerificationLink(Acteurs acteur, String lienDeVerification) {

        // Envoyer un mail de verification a l'acteur avec le lien de verification
        var emailDestinataire = acteur.getEmailPersonnel();
        var objetMessage = "Confirmation de creation de compte";
        String contenuMessage = MessageTemplates.nouveauActeurMail(acteur.getPrenom(), lienDeVerification);
        coursier.envoyerEmail(emailDestinataire, contenuMessage, objetMessage);
    }

    @Override
    public void generateAndSendVerificationLink(Acteurs acteur, String chemin) {
        String lien = generateVerificationUrl(acteur, chemin);
        sendVerificationLink(acteur, lien);

    }

    /* Logique pour générer un token de vérification unique */
    private static String generateVerificationToken() {
        return java.util.UUID.randomUUID().toString();
    }

}
