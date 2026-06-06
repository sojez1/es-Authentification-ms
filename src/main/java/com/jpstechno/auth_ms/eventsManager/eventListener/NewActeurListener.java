package com.jpstechno.auth_ms.eventsManager.eventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jpstechno.auth_ms.eventsManager.eventsPublisher.NewActeurEvent;
import com.jpstechno.auth_ms.services.UrlVerificationServ;

import jakarta.mail.MessagingException;

@Component
public class NewActeurListener {

    @Autowired
    private UrlVerificationServ emailVerification;
    private final static Logger log = LoggerFactory.getLogger(NewActeurEvent.class);

    @EventListener
    @Async
    public void handleNewActeurEvent(NewActeurEvent newActeurEvent) {

        // Envoyer un email de verification à l'acteur avec le lien de verification
        try {
            emailVerification.generateVerificationUrl(newActeurEvent.getActeur());
        } catch (MessagingException ex) {
            log.error("Echec lors de l'envoi du mail", ex.getMessage());

        }

    }
}
