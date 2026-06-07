package com.jpstechno.auth_ms.eventsManager.eventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jpstechno.auth_ms.eventsManager.eventsPublisher.NewActeurEvent;
import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.services.UrlVerificationServ;

@Component
public class NewActeurListener {

    @Autowired
    private UrlVerificationServ emailVerification;

    @EventListener
    @Async
    public void handleNewActeurEvent(NewActeurEvent newActeurEvent) {

        String chemin = "/newActeur/confirmer/email";
        Acteurs acteur = (Acteurs) newActeurEvent.getSource();

        // Envoyer un email de verification à l'acteur avec le lien de verification
        emailVerification.generateAndSendVerificationLink(acteur, chemin);

    }
}
