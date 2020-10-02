package com.wisenut.web.payload;

import com.wisenut.domain.application.commands.CreateTeamCommand;

public class CreateTeamPayload {

    private String name;

    public CreateTeamCommand toCommand(Long userId) {
        return new CreateTeamCommand(userId, name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
