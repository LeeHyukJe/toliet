package com.wisenut.domain.model.user;

import com.wisenut.domain.common.event.UserRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

public class UserRegisteredEventHandler {
    private final static Logger log = LoggerFactory.getLogger(UserRegisteredEventHandler.class);

    @EventListener(UserRegisteredEventHandler.class)
    public void handleEvent(UserRegisteredEvent event){
        log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());
    }
}
