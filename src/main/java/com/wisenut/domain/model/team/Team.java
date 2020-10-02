package com.wisenut.domain.model.team;

import com.wisenut.domain.common.model.AbstractBaseEntity;
import com.wisenut.domain.model.user.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@EqualsAndHashCode(of = {"userId", "name"})
@ToString
@Entity
@Table(name = "team")
public class Team extends AbstractBaseEntity {
    private static final long serialVersionUID = -2264390861852998965L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

//    @Column(name = "col_userId")
//    private long userId;

    @Column(name = "archived")
    private boolean archived;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public static Team create(String name, User user) {
        Team team = new Team();
        team.name = name;
        team.archived = false;
        team.user = user;
        team.createdDate = new Date();
        return team;
    }

    @Builder
    public Team (Long id, String name, boolean archived, Date createdDate) {
        this.id = id;
        this.name = name;
        this.archived = archived;
        this.createdDate = createdDate;
    }

    public Team() {}
}
