package com.wisenut.web.apis;

import com.wisenut.domain.application.BoardService;
import com.wisenut.domain.application.TeamService;
import com.wisenut.domain.common.security.CurrentUser;
import com.wisenut.domain.model.board.Board;
import com.wisenut.domain.model.team.Team;
import com.wisenut.domain.model.user.SimpleUser;
import com.wisenut.web.results.ApiResult;
import com.wisenut.web.results.MyDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MeApiController {

    private final TeamService teamService;
    private final BoardService boardService;

    @GetMapping("/api/me")
    public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
        List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
        List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
        return MyDataResult.build(currentUser, teams, boards);
    }
}
