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

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlVerificationImplementation implements UrlVerificationServ {

    private final Coursier coursier;

    private final UrlVerificationRepos urlVerificationRepos;

    @Override
    public void generateVerificationUrl(Acteurs acteur) throws MessagingException {

        // 1. Generer le token de verification
        String verificationToken = generateVerificationToken();

        // 2. Creer url de verification
        String verificationUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/acteurs/verification")
                .queryParam("id", acteur.getActeurId())
                .queryParam("token", verificationToken)
                .toUriString();

        UrlVerification urlVerification = urlVerificationRepos.findById(acteur.getActeurId()).orElse(null);

        if (urlVerification == null) { // Si aucune URL de vérification n'existe pour cet acteur, en créer une nouvelle

            urlVerification = new UrlVerification();
            urlVerification.setActeurEcole(acteur);
            urlVerification.setVerificationToken(verificationToken);
            urlVerification.setUsed(false);
            urlVerification
                    .setExpirationTime(System.currentTimeMillis() + ConstanteParams.dureeValiditeMessage * 60 * 1000);

        } else { // Sinon, mettre à jour l'URL de vérification existante
            urlVerification.setVerificationToken(verificationToken);
            urlVerification.setUsed(false);
            urlVerification
                    .setExpirationTime(System.currentTimeMillis() + ConstanteParams.dureeValiditeMessage * 60 * 1000); // minutes
        }

        urlVerificationRepos.save(urlVerification);

        // Envoyer un mail de verification a l'acteur avec le lien de verification
        var emailDestinataire = acteur.getEmailPersonnel();
        var objetMessage = "Confirmation de creation de compte";
        String contenuMessage = MessageTemplates.nouveauActeurMail(acteur.getPrenom(), verificationUrl, objetMessage);
        coursier.envoyerEmail(emailDestinataire, contenuMessage, objetMessage);

    }

    /* Logique pour générer un token de vérification unique */
    private static String generateVerificationToken() {
        return java.util.UUID.randomUUID().toString();
    }

}
