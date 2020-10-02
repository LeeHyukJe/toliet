package com.wisenut.domain.application;

import com.wisenut.domain.application.commands.CreateTeamCommand;
import com.wisenut.domain.model.team.Team;

import java.util.List;

public interface TeamService {
    List<Team> findTeamsByUserId(Long userId);
    Team createTeam(CreateTeamCommand command);
}
