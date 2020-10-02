package com.wisenut.infrastructures.repository.team;

import com.wisenut.domain.model.team.Team;

import java.util.List;

public interface TeamRepositoryCustom {
    List<Team> findTeamsByUserId(Long userId);
}
