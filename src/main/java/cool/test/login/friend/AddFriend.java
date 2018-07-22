package cool.test.login.friend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AddFriend {

    @Id
    @GeneratedValue //自增的
    private long id;

    private long inviterId;

    private long inviteedId;

    private String inviteedUserName;

    private String inviterUserName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInviterId() {
        return inviterId;
    }

    public void setInviterId(long inviterId) {
        this.inviterId = inviterId;
    }

    public long getInviteedId() {
        return inviteedId;
    }

    public void setInviteedId(long inviteedId) {
        this.inviteedId = inviteedId;
    }

    public String getInviteedUserName() {
        return inviteedUserName;
    }

    public void setInviteedUserName(String inviteedUserName) {
        this.inviteedUserName = inviteedUserName;
    }

    public String getInviterUserName() {
        return inviterUserName;
    }

    public void setInviterUserName(String inviterUserName) {
        this.inviterUserName = inviterUserName;
    }

    @Override
    public String toString() {
        return "AddFriend{" +
                "id=" + id +
                ", inviterId=" + inviterId +
                ", inviteedId=" + inviteedId +
                ", inviteedUserName='" + inviteedUserName + '\'' +
                ", inviterUserName='" + inviterUserName + '\'' +
                '}';
    }
}
