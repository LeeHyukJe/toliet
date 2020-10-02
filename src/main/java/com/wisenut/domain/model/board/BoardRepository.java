package com.wisenut.domain.model.board;

import com.wisenut.infrastructures.repository.board.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
