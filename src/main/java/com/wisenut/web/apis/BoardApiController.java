package com.wisenut.web.apis;

import com.wisenut.domain.application.BoardService;
import com.wisenut.domain.common.security.CurrentUser;
import com.wisenut.domain.model.board.Board;
import com.wisenut.domain.model.user.SimpleUser;
import com.wisenut.web.payload.CreateBoardPayload;
import com.wisenut.web.results.ApiResult;
import com.wisenut.web.results.CreateBoardResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity<ApiResult> createBoard (@RequestBody CreateBoardPayload payload, @CurrentUser SimpleUser currentUser) {
        Board board = boardService.createBoard(payload.toCommand(currentUser.getUserId()));
        return CreateBoardResult.build(board);
    }
}
