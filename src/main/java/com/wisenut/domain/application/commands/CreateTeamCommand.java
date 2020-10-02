package com.wisenut.domain.application.commands;

import com.wisenut.domain.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class CreateTeamCommand {
    private final Long userId;
    private final String name;
}
