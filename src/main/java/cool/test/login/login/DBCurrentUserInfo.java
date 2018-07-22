package cool.test.login.login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DBCurrentUserInfo extends JpaRepository<CurrentUserInfo, Long> {

    public List<CurrentUserInfo> findByUsername(String userName);


    public List<CurrentUserInfo> findByToken(String token);

    public List<CurrentUserInfo> findByPhoneNumber(String phoneNumber);


    public CurrentUserInfo findById(long userId);

}
