package com.wisenut.domain.application;

import com.wisenut.domain.application.commands.RegistrationCommand;
import com.wisenut.domain.model.user.RegistrationException;

public interface UserService {
    void register(RegistrationCommand command) throws RegistrationException, RegistrationException;
}
