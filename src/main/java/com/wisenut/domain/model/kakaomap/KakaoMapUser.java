package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.IUser;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Component
@Getter
@NoArgsConstructor
@Entity
@Table(name = "User")
public class KakaoMapUser implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, length = 50, unique = true)
    private String username;
    @Column(name="email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;
    @Column(name="password", nullable = false, length = 30)
    private String password;
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private List<KakaoMapSearch> searchList;

    @Transient
    private IMapOffer iMapOffer;

    @Builder
    public KakaoMapUser(Long id, String username, String emailAddress, String password, String firstName, String lastName, Date createdDate) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
    }

    @Autowired
    public KakaoMapUser(IMapOffer iMapOffer){
        this.iMapOffer = iMapOffer;
    }
    @Override
    public IMapOffer search(String stationName) {
        iMapOffer.searchStation(stationName);
        return iMapOffer;
    }
}
