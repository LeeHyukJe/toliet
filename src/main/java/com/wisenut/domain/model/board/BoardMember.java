package com.wisenut.domain.model.board;

import com.wisenut.domain.common.model.AbstractBaseEntity;
import com.wisenut.domain.model.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "board_memeber")
public class BoardMember extends AbstractBaseEntity {
    private static final long serialVersionUID = 1101935717986500672L;

    @EmbeddedId
    private BoardMemberId id;


    public static BoardMember create(Long boardId, Long userId){
        BoardMember boardMember = new BoardMember();
        User user = User.builder().id(userId).build();
        Board board = Board.builder().id(boardId).build();
        boardMember.id = new BoardMemberId(board, user);
        return boardMember;
    }

    @Getter
    @ToString
    @Embeddable
    public static class BoardMemberId implements Serializable{
//        @Column(name = "board_id")
//        private Long boardId;
//
//        @Column(name = "user_id")
//        private Long userId;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id")
        private User user;
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "board_id")
        private Board board;
//
        private BoardMemberId (Board board, User user){
            this.user = user;
            this.board = board;
        }

//        private BoardMemberId (Long boardId, Long userId){
//            this.boardId = boardId;
//            this.userId = userId;
//        }

        public BoardMemberId() {

        }
    }



}
