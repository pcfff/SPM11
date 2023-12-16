package controller;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/{username}", configurator = CustomConfigurator.class)
public class ChatEndPoint {

    private static Map<String, Session> userSessions = new ConcurrentHashMap<>();
    private static Map<String, Map<String, List<String>>> messages = new ConcurrentHashMap<>();

    private ServletContext servletContext;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        System.out.println("拿到信息没");
        // 可以使用session(Http的session  传参传进来的是Websocket的session)
        // 这里取的东西要和配置里寸的东西一一对应
        this.servletContext = (ServletContext) session.getUserProperties().get("servletContext");
        List<String > userList = (List<String>)servletContext.getAttribute("userList");
        for (String s : userList) {
            System.out.println("存在的用户有：" + s);
        }
        userSessions.put(username, session);
        // 发送未读消息
        sendUnreadMessages(username, session);
    }

    @OnMessage
    public void onMessage(Session session,String message, @PathParam("username") String sender) {
        // 解析消息格式
    }

    private void sendUnreadMessages(String username, Session session) {
        messages.getOrDefault(username, new HashMap<>()).values().forEach(msgList -> {
            msgList.forEach(msg -> sendDirectMessage(session, msg));
        });
        messages.remove(username); // 移除已发送的未读消息
    }

    private void storeMessage(String sender, String receiver, String message) {
        messages.computeIfAbsent(receiver, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(sender, k -> new ArrayList<>())
                .add(message);
    }

    private void sendDirectMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        userSessions.remove(username);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // 处理错误
    }
}
