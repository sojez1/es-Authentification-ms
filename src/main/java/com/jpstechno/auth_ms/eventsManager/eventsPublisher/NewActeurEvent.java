package com.jpstechno.auth_ms.eventsManager.eventsPublisher;

import org.springframework.context.ApplicationEvent;

import com.jpstechno.auth_ms.modeles.Acteurs;

import lombok.Getter;

@Getter
public class NewActeurEvent extends ApplicationEvent {

    private final Acteurs acteur;

    public NewActeurEvent(Object source, Acteurs acteur) {
        super(source);
        this.acteur = acteur;
    }

}
