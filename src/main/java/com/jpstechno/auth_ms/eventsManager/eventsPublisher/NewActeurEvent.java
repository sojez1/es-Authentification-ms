package com.jpstechno.auth_ms.eventsManager.eventsPublisher;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class NewActeurEvent extends ApplicationEvent {

    public NewActeurEvent(Object source) {
        super(source);
    }

}
