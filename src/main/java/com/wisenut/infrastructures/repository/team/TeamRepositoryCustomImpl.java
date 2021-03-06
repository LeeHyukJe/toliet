package com.wisenut.infrastructures.repository.team;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wisenut.domain.model.board.QBoard;
import com.wisenut.domain.model.board.QBoardMember;
import com.wisenut.domain.model.team.QTeam;
import com.wisenut.domain.model.team.Team;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Team> findTeamsByUserId(Long userId) {
        List<Team> team = queryFactory.select(QTeam.team)
                .from(QTeam.team)
                .where(QTeam.team.user.id.eq(userId))
                .fetch();
        List<Team> team2 = queryFactory.select(QTeam.team)
                .from(QTeam.team)
                .innerJoin(QBoard.board).on(QTeam.team.id.eq(QBoard.board.team.id))
                .innerJoin(QBoardMember.boardMember).on(QBoard.board.id.eq(QBoardMember.boardMember.id.board.id))
                .fetch();

//        team.addAll(team2);
        Set<Team> union = team
                .stream()
                .distinct()
                .filter(ele -> team2.contains(ele))
                .collect(Collectors.toSet());

        List result = new ArrayList<>(union);

        return result;
    }
}
