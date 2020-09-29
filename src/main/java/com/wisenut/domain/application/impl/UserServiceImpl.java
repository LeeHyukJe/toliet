package com.wisenut.domain.application.impl;

import com.mysema.commons.lang.Assert;
import com.wisenut.domain.application.UserService;
import com.wisenut.domain.application.commands.RegistrationCommand;
import com.wisenut.domain.common.event.DomainEventPublisher;
import com.wisenut.domain.common.event.UserRegisteredEvent;
import com.wisenut.domain.common.mail.MailManager;
import com.wisenut.domain.common.mail.MessageVariable;
import com.wisenut.domain.model.user.RegistrationException;
import com.wisenut.domain.model.user.RegistrationManagement;
import com.wisenut.domain.model.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private RegistrationManagement registrationManagement;
    private DomainEventPublisher domainEventPublisher;
    private MailManager mailManager;

    public UserServiceImpl(RegistrationManagement registrationManagement, DomainEventPublisher domainEventPublisher, MailManager mailManager){
        this.registrationManagement = registrationManagement;
        this.domainEventPublisher = domainEventPublisher;
        this.mailManager = mailManager;
    }
    @Override
    public void register(RegistrationCommand command) throws RegistrationException {
        Assert.notNull(command, "Parameter `command`는 null 값일 수 없습니다.");
        User newUser = registrationManagement.register(
                command.getUsername(),
                command.getEmailAddress(),
                command.getPassword());

        sendWelcomeMessage(newUser);
        domainEventPublisher.publish(new UserRegisteredEvent(newUser));
    }

    private void sendWelcomeMessage(User user) {
        mailManager.send(
                user.getEmailAddress(),
                "Welcome to TaskAgile",
                "welcome.ftl",
                MessageVariable.from("user", user)
        );
    }
}
