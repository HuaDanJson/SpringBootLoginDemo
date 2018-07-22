package cool.test.login.friend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import cool.test.login.login.DBCurrentUserInfo;

@RestController
@RequestMapping("/friend")
public class FriendControl {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DBCurrentUserInfo dbCurrentUserInfo;

    @Autowired
    private DBAddFriend dbAddFriend;

    @Autowired
    private DBFriend dbFriend;

    @GetMapping("/getAddFriendList/{userId}")
    public List<AddFriend> getAddFriendList(@PathVariable long userId) {
        logger.info("FriendControl getAddFriendList  userId = " + userId);
        return dbAddFriend.findByInviteedId(userId);
    }

    @GetMapping("/getFriendList/{userId}")
    public List<Friend> getFriendList(@PathVariable long userId) {
        logger.info("FriendControl getAddFriendList  userId = " + userId);
        return dbFriend.findByUserId(userId);
    }

    @PostMapping("/addFriend")
    public AddFriend addFriend(@RequestBody AddFriend addFriend) {
        logger.info("CurrentUserInfoControl addFriend  AddFriend = " + addFriend);
        return dbAddFriend.save(addFriend);
    }

    @PostMapping("/acceptAddFriend")
    public Object acceptAddFriend(@RequestBody AddFriend addFriend) {
        logger.info("CurrentUserInfoControl acceptAddFriend  AddFriend = " + addFriend);
        HashMap<String, Integer> hashMap = new HashMap<>();
        AddFriend addFriend1 = dbAddFriend.findByInviterIdAndInviteedId(addFriend.getInviterId(), addFriend.getInviteedId());
        Friend friend = new Friend();
        friend.setFriendId(addFriend1.getInviteedId());
        friend.setUserId(addFriend1.getInviterId());
        friend.setFriendUserName(addFriend1.getInviteedUserName());
        dbFriend.save(friend);
        Friend friend2 = new Friend();
        friend2.setFriendId(addFriend1.getInviterId());
        friend2.setUserId(addFriend1.getInviteedId());
        friend2.setFriendUserName(addFriend1.getInviterUserName());
        dbFriend.save(friend2);
        hashMap.put("result", 1);
        dbAddFriend.delete(addFriend1);
        return hashMap;
    }

}
