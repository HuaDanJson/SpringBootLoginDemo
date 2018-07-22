package cool.test.login.websocket;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{userId}")
@Component
@RestController
public class WebSocketService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int onlineCount = 0;
    private static HashMap<Long, Session> sessionHashMap = new HashMap<>();
    private static HashMap<String, Long> sessioIds = new HashMap<>();
    private Session session;

    @OnOpen
    public void onOpen(Session mSession, @PathParam(value = "userId") long userId) throws IOException {
        this.session = mSession;
        sessionHashMap.put(userId, session);
        sessioIds.put(session.getId(), userId);
        incrOnlineCount();
        logger.info("new connection...current online count: {}", getOnlineCount());
        logger.info("WebSocketService current online count: {}", getOnlineCount());
        logger.info("WebSocketService current online sessionHashMap: {}", sessionHashMap);
        logger.info("WebSocketService current online sessionHashMap.size(): {}", sessionHashMap.size());
    }

    @OnClose
    public void onClose() throws IOException {
        sessionHashMap.remove(sessioIds.get(session.getId()));
        sessioIds.remove(session.getId());
        logger.info("one connection closed...current online count: {}", getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("message received:  {}", message);
        logger.info("session received: {}", session);
        // broadcast received message
        WebSocketMessage socketMessage = JSON.parseObject(message, WebSocketMessage.class);
        if (socketMessage != null && socketMessage.getData() != null && socketMessage.getData().getType() == 1) {
//            sessionHashMap.put(socketMessage.getData().getSenderId(), session);
//            sessioIds.put(session.getId(), socketMessage.getData().getSenderId());
        } else if (socketMessage != null && socketMessage.getData() != null && socketMessage.getData().getType() == 2) {
            logger.info("send message  socketMessage.getData().getSenderId() =  " + socketMessage.getData().getSenderId() + "   socketUser.get(socketMessage.getData().getSenderId()) = " + sessionHashMap.get(socketMessage.getData().getSenderId()));
            logger.info("send message  socketMessage.getData().getFriendId()) = " + socketMessage.getData().getFriendId() + "   socketUser.get(socketMessage.getData().getFriendId() = " + sessionHashMap.get(socketMessage.getData().getFriendId()));
            sendMessage(message, sessionHashMap.get(socketMessage.getData().getSenderId()));
            sendMessage(message, sessionHashMap.get(socketMessage.getData().getFriendId()));
        }
        logger.info("WebSocketService session received  socketMessage = " + socketMessage);
    }

    public void sendMessage(String message, Session session) throws IOException {
        if (session != null && session.getBasicRemote() != null) {
            session.getBasicRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return WebSocketService.onlineCount;
    }

    public static synchronized void incrOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void decOnlineCount() {
        WebSocketService.onlineCount--;
    }

}
