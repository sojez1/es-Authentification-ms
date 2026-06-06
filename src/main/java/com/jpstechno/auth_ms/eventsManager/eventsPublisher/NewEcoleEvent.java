package com.jpstechno.auth_ms.eventsManager.eventsPublisher;

import org.springframework.context.ApplicationEvent;

public class NewEcoleEvent extends ApplicationEvent {

    public NewEcoleEvent(Object source) {
        super(source);
    }

}
