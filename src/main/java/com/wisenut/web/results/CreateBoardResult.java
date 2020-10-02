package com.wisenut.web.results;

import com.wisenut.domain.model.board.Board;
import org.springframework.http.ResponseEntity;

public class CreateBoardResult {

    public static ResponseEntity<ApiResult> build(Board board) {
        ApiResult apiResult = ApiResult.blank()
                .add("id", board.getId())
                .add("name", board.getName())
                .add("description", board.getDescription());
                //.add("teamId", board.getTeamId());
        return Result.ok(apiResult);
    }
}
