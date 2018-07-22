package cool.test.login.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DBAddFriend extends JpaRepository<AddFriend, Long> {

    List<AddFriend> findByInviteedId(long inviterId);

    AddFriend findByInviterIdAndInviteedId(long inviterId, long invteeId);

}
