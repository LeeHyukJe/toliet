package com.wisenut.domain.model.board;

import com.wisenut.domain.model.team.Team;
import com.wisenut.domain.model.team.TeamRepository;
import com.wisenut.domain.model.user.User;
import com.wisenut.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BoardManagement {

    private final BoardRepository boardRepository;
    private final BoardMemberRepository boardMemberRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public Board createBoard(Long creatorId, String name, String description, Long teamId) {
        User user = userRepository.findById(creatorId).orElse(null);
        Team team = teamRepository.findById(teamId).orElse(null);
        Board board = Board.create(user, name,
                description, team);
        boardRepository.save(board);
        // Add the creator to as a board member
        BoardMember boardMember = BoardMember.create(board.getId(), creatorId);
        boardMemberRepository.save(boardMember);
        return board;
    }
}
