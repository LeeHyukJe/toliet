package com.wisenut.infrastructures.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wisenut.domain.model.board.Board;
import com.wisenut.domain.model.board.QBoard;
import com.wisenut.domain.model.board.QBoardMember;
import com.wisenut.domain.model.team.QTeam;
import com.wisenut.domain.model.team.Team;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Board> findBoardsByMembership(Long userId) {
        List<Board> boards = queryFactory
                .select(QBoard.board)
                .from(QBoard.board)
                .leftJoin(QBoardMember.boardMember).on(QBoard.board.id.eq(QBoardMember.boardMember.id.board.id))
                .where(QBoardMember.boardMember.id.user.id.eq(userId))
                .fetch();
        return boards;
    }
}
