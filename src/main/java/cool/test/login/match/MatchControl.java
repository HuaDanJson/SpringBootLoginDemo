package cool.test.login.match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")

public class MatchControl {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/v1.3/match_request/{userId}")
    private MatchInfo matchRequest(@PathVariable int userId) {
        MatchInfo matchInfo = new MatchInfo();
        matchInfo.setSenderId(123);
        matchInfo.setChannelKey(null);
        matchInfo.setChannelName("demoChannel1");
        matchInfo.setCreatAt(System.currentTimeMillis());
        return matchInfo;
    }

}
