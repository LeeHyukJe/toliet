package com.wisenut.domain.application.impl;

import com.mysema.commons.lang.Assert;
import com.wisenut.domain.application.UserService;
import com.wisenut.domain.application.commands.RegistrationCommand;
import com.wisenut.domain.common.event.DomainEventPublisher;
import com.wisenut.domain.common.event.UserRegisteredEvent;
import com.wisenut.domain.common.mail.MailManager;
import com.wisenut.domain.common.mail.MessageVariable;
import com.wisenut.domain.model.user.*;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private RegistrationManagement registrationManagement;
    private DomainEventPublisher domainEventPublisher;
    private MailManager mailManager;
    private UserRepository userRepository;

    public UserServiceImpl(RegistrationManagement registrationManagement, DomainEventPublisher domainEventPublisher, MailManager mailManager, UserRepository userRepository){
        this.registrationManagement = registrationManagement;
        this.domainEventPublisher = domainEventPublisher;
        this.mailManager = mailManager;
        this.userRepository = userRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("이용자가 존재하지 않습니다.");
        }

        User user;
        if(username.contains("@")){
            user = userRepository.findByEmailAddress(username);
        }else {
            user = userRepository.findByUsername(username);
        }

        if(user == null){
            throw new UsernameNotFoundException(username + "에 대한 사용자가 존재하지 않습니다.");
        }
        return new SimpleUser(user);
    }
}
