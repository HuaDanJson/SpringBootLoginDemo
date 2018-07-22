package cool.test.login.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DBFriend extends JpaRepository<Friend, Long> {

    public List<Friend> findByUserId(long userId);

}
