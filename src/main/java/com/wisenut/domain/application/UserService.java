package com.wisenut.domain.application;

import com.wisenut.domain.application.commands.RegistrationCommand;
import com.wisenut.domain.model.user.RegistrationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(RegistrationCommand command) throws RegistrationException, RegistrationException;
}
