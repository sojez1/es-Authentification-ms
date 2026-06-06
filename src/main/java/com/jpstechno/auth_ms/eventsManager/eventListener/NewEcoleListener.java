package com.jpstechno.auth_ms.eventsManager.eventListener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Async
public class NewEcoleListener {

    @EventListener
    public void ValiderEnregistrementecole() {

    }

}
