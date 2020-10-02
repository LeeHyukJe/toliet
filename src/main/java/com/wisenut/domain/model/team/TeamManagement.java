package com.wisenut.domain.model.team;

import com.wisenut.domain.model.user.User;
import com.wisenut.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeamManagement {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public Team createTeam(String teamName, Long userId){
        User user = userRepository.findById(userId).orElse(null);
        Team team = Team.create(teamName, user);
        teamRepository.save(team);
        return team;
    }
}
