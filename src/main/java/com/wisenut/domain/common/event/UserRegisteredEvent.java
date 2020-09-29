package com.wisenut.domain.common.event;


import com.wisenut.domain.model.user.User;
import org.springframework.util.Assert;

public class UserRegisteredEvent extends DomainEvent {

    private static final long serialVersionUID = 2580061707540917880L;

    private User user;

    public UserRegisteredEvent(User user) {
        super(user);
        Assert.notNull(user, "Parameter `user` must not be null");
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRegisteredEvent that = (UserRegisteredEvent) o;
        return that.user.equals(this.user);
    }

    public int hashCode() {
        return this.user.hashCode();
    }

    public String toString() {
        return "UserRegisteredEvent{" +
                "user='" + user + '\'' +
                "timestamp='" + getTimestamp() + '\'' +
                '}';
    }
}
