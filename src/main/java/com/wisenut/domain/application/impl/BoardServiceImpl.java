package com.wisenut.domain.application.impl;

import com.wisenut.domain.application.BoardService;
import com.wisenut.domain.application.commands.CreateBoardCommand;
import com.wisenut.domain.common.event.DomainEventPublisher;
import com.wisenut.domain.model.board.Board;
import com.wisenut.domain.model.board.BoardManagement;
import com.wisenut.domain.model.board.BoardRepository;
import com.wisenut.domain.model.board.events.BoardCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardManagement boardManagement;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public List<Board> findBoardsByMembership(Long userId) {
        return boardRepository.findBoardsByMembership(userId);
    }

    @Override
    public Board createBoard(CreateBoardCommand command) {
        Board board = boardManagement.createBoard(command.getUserId(), command.getName(), command.getDescription(), command.getTeamId());
        domainEventPublisher.publish(new BoardCreatedEvent(this, board));
        return board;
    }
}
