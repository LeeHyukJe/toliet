package com.wisenut.domain.application.impl;

import com.wisenut.domain.application.UserService;
import com.wisenut.domain.application.commands.RegistrationCommand;
import com.wisenut.domain.model.user.RegistrationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void register(RegistrationCommand command) throws RegistrationException {
        //TODO do something...
    }
}
