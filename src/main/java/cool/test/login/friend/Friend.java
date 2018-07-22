package cool.test.login.friend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Friend {

    @Id
    @GeneratedValue //自增的
    private long id;

    private long userId;

    private long friendId;

    private String friendUserName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userId=" + userId +
                ", friendId=" + friendId +
                ", friendUserName='" + friendUserName + '\'' +
                '}';
    }
}
