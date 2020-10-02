package com.wisenut.domain.application.impl;

import com.wisenut.domain.application.TeamService;
import com.wisenut.domain.application.commands.CreateTeamCommand;
import com.wisenut.domain.common.event.DomainEventPublisher;
import com.wisenut.domain.model.team.Team;
import com.wisenut.domain.model.team.TeamManagement;
import com.wisenut.domain.model.team.TeamRepository;
import com.wisenut.domain.model.team.events.TeamCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final TeamManagement teamManagement;


    @Override
    public List<Team> findTeamsByUserId(Long userId) {
        return teamRepository.findTeamsByUserId(userId);
    }

    @Override
    public Team createTeam(CreateTeamCommand command) {
        Team team= teamManagement.createTeam(command.getName(), command.getUserId());
        domainEventPublisher.publish(new TeamCreatedEvent(this, team));
        return team;
    }
}
