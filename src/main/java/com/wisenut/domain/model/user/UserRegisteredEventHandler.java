package com.wisenut.domain.model.user;

import com.wisenut.domain.model.user.event.UserRegisteredEvent;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(UserRegisteredEventHandler.class);

    @EventListener(UserRegisteredEvent.class)
    public void handleEvent(UserRegisteredEvent event){
        log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());
    }
}
