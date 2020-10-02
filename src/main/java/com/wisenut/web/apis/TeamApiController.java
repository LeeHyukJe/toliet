package com.wisenut.web.apis;

import com.wisenut.domain.application.TeamService;
import com.wisenut.domain.common.security.CurrentUser;
import com.wisenut.domain.model.team.Team;
import com.wisenut.domain.model.user.SimpleUser;
import com.wisenut.web.payload.CreateTeamPayload;
import com.wisenut.web.results.ApiResult;
import com.wisenut.web.results.CreateTeamResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class TeamApiController {

    private final TeamService teamService;

    @PostMapping("/api/teams")
    public ResponseEntity<ApiResult> createTeam (@RequestBody CreateTeamPayload payload, @CurrentUser SimpleUser currentUser) {
        Team team = teamService.createTeam(payload.toCommand(currentUser.getUserId()));
        return CreateTeamResult.build(team);
    }
}
