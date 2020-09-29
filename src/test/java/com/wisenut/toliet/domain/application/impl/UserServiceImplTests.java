package com.wisenut.toliet.domain.application.impl;

import com.wisenut.domain.application.commands.RegistrationCommand;
import com.wisenut.domain.application.impl.UserServiceImpl;
import com.wisenut.domain.common.event.DomainEventPublisher;
import com.wisenut.domain.common.event.UserRegisteredEvent;
import com.wisenut.domain.common.mail.MailManager;
import com.wisenut.domain.common.mail.MessageVariable;
import com.wisenut.domain.model.user.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserServiceImplTests {

    private RegistrationManagement registationManagementMock;
    private DomainEventPublisher domainEventPublisherMock;
    private MailManager mailManagerMock;
    private UserServiceImpl instance;

    @Before
    public void setup(){
        registationManagementMock = mock(RegistrationManagement.class);
        domainEventPublisherMock = mock(DomainEventPublisher.class);
        mailManagerMock = mock(MailManager.class);
        instance = new UserServiceImpl(registationManagementMock, domainEventPublisherMock, mailManagerMock);
    }

    @Test(expected = RegistrationException.class)
    public void register_existingUsername_shouldFail() throws RegistrationException {
        String username = "existing";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";
        doThrow(UsernameExistsException.class).when(registationManagementMock)
                .register(username, emailAddress, password);

        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
        instance.register(command);
    }

    @Test(expected = RegistrationException.class)
    public void register_existingEmailAddress_shouldFail() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "existing@taskagile.com";
        String password = "MyPassword!";
        doThrow(EmailAddressExistsException.class).when(registationManagementMock)
                .register(username, emailAddress, password);

        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
        instance.register(command);
    }

    @Test
    public void register_validCommand_shouldSucceed() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";
        User newUser = User.create(username, emailAddress, password);
        when(registationManagementMock.register(username, emailAddress, password))
                .thenReturn(newUser);
        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

        instance.register(command);

        verify(mailManagerMock).send(
                emailAddress,
                "Welcome to TaskAgile",
                "welcome.ftl",
                MessageVariable.from("user", newUser)
        );
        verify(domainEventPublisherMock).publish(new UserRegisteredEvent(newUser));
    }
}
