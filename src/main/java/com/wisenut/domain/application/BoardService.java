package com.wisenut.domain.application;

import com.wisenut.domain.application.commands.CreateBoardCommand;
import com.wisenut.domain.model.board.Board;
import com.wisenut.domain.model.user.UserId;

import java.util.List;

public interface BoardService {
    List<Board> findBoardsByMembership(Long userId);
    Board createBoard(CreateBoardCommand command);
}
