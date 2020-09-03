package com.wisenut.domain.model.kakaomap;

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
public class KakaoMapSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    // userid foreign key
    private Long userid;
    // map foreign key
    private Long mapid;
}
