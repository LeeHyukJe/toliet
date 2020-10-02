package com.wisenut.domain.application.commands;

import com.wisenut.domain.model.team.Team;
import com.wisenut.domain.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class CreateBoardCommand {
    private final long userId;
    private final String name;
    private final String description;
    private final long teamId;
}
