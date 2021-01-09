package com.wisenut.domain.model.user;

import com.wisenut.domain.model.user.event.UserRegisteredEvent;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UserRegisteredEventHandler {

    @EventListener(UserRegisteredEvent.class)
    public void handleEvent(UserRegisteredEvent event){
        log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());
    }
}
