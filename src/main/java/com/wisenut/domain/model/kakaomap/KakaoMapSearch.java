package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.IMapSearch;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "kakaomapsearch")
public class KakaoMapSearch implements IMapSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    // userid foreign key
    private Long userid;
    // map foreign key
    private Long mapid;

    @Override
    public void newSearch(Long id, Date createdDate, Long userId, Long mapId) {
        this.id = id;
        this.createdDate = createdDate;
        this.userid = userId;
        this.mapid = mapId;
    }
}
