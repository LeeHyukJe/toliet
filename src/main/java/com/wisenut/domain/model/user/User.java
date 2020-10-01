package com.wisenut.domain.model.user;

import com.wisenut.domain.common.model.AbstractBaseEntity;
import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.kakaomap.KakaoMapSearch;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@EqualsAndHashCode(of = {"username","emailAddress"})
@ToString
@Table(name = "user")
public class User extends AbstractBaseEntity {

    private static final long serialVersionUID = -538781580460070724L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;


    public User() {
    }

    /**
     * Create new user during registration
     */
    public static User create(String username, String emailAddress, String password) {
        User user = new User();
        user.username = username;
        user.emailAddress = emailAddress;
        user.password = password;
        user.firstName = "";
        user.lastName = "";
        user.createdDate = new Date();
        return user;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }



    public void updateName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private List<KakaoMapSearch> searchList;

    @Transient
    private IMapOffer iMapOffer;

    @Builder
    public User(Long id, String username, String emailAddress, String password, String firstName, String lastName, Date createdDate) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
    }

    public User(IMapOffer iMapOffer){
        this.iMapOffer = iMapOffer;
    }
    public IMapOffer search(String stationName) {
        iMapOffer.searchStation(stationName);
        return iMapOffer;
    }
}
