package com.wisenut.domain.model.board;

import com.wisenut.domain.common.model.AbstractBaseEntity;
import com.wisenut.domain.model.team.Team;
import com.wisenut.domain.model.user.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@ToString
@EqualsAndHashCode(of = {"userId","teamId"})
@Table(name = "board")
public class Board extends AbstractBaseEntity {
    private static final long serialVersionUID = -2264390861852998965L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 128, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 256, unique = true)
    private String description;

//    @Column(name = "col_user_id")
//    private long userId;
//
//    @Column(name = "col_team_id")
//    private Long teamId;

    @Column(name = "archived")
    private boolean archived;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public static Board create(User user, String name, String description, Team team) {
        Board board = new Board();
        board.user = user;
        board.name = name;
        board.description = description;
        board.team = team;
        board.archived = false;
        board.createdDate = new Date();
        return board;
    }

    @Builder
    public Board(Long id, String name, boolean archived, Date createdDate) {
        this.id = id;
        this.name = name;
        this.archived = archived;
        this.createdDate = createdDate;
    }

    public Board (){}
}
