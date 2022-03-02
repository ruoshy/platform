package cn.ruoshy.platform.service;

import cn.ruoshy.platform.util.ContextUtil;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/w/{gid}/{username}")
@Service
public class WebSocketService {

    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    private static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public static void send2User(String userName, String message) {
        try {
            sendMessage(sessionPools.get(userName), message);
        } catch (IOException e) {
            System.out.println("send message error -- " + userName);
        }
    }

    public static void send2Group(LinkedList<String> userNames, String message) {
        for (int i = 0; i < userNames.size(); i++) {
            try {
                sendMessage(sessionPools.get(userNames.get(i)), message);
            } catch (IOException e) {
                System.out.println("send message error -- " + userNames.get(i));
                userNames.remove(i);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String userName) {
        sessionPools.put(userName, session);
//        System.out.println("add " + userName);
    }

    @OnClose
    public void onClose(@PathParam("username") String userName) {
        sessionPools.remove(userName);
//        System.out.println("close " + userName);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String userName, String message) {
        GameShuntService gameShuntService = ContextUtil.getBean(GameShuntService.class);
        gameShuntService.handle(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
//        throwable.printStackTrace();
    }

//    public static boolean isOnLine(String name) {
//        return sessionPools.get(name) != null;
//    }
}
