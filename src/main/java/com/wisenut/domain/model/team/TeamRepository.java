package com.wisenut.domain.model.team;

import com.wisenut.infrastructures.repository.team.TeamRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {
}
