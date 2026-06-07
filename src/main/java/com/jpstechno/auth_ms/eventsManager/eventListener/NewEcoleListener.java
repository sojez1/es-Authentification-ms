package com.jpstechno.auth_ms.eventsManager.eventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jpstechno.auth_ms.eventsManager.eventsPublisher.NewEcoleEvent;
import com.jpstechno.auth_ms.modeles.ActeurEcoles;
import com.jpstechno.auth_ms.modeles.Ecoles;
import com.jpstechno.auth_ms.services.UrlVerificationServ;

@Component
@Async
public class NewEcoleListener {

    @Autowired
    private UrlVerificationServ urlVerification;

    @EventListener
    public void ValiderEnregistrementecole(NewEcoleEvent newEcoleEvent) {
        String chemin = "/ecoles/newEcole/confirmation/acteur";
        Ecoles ecole = (Ecoles) newEcoleEvent.getSource();

        for (ActeurEcoles acteur : ecole.getListeActeurEcole()) {
            urlVerification.generateAndSendVerificationLink(acteur.getActeur(), chemin);
        }
    }

}
