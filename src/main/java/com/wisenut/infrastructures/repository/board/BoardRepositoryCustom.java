package com.wisenut.infrastructures.repository.board;

import com.wisenut.domain.model.board.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> findBoardsByMembership(Long userId);
}
