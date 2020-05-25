package app.handler;//package app.handler;
//
//import app.Logger;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//@Component
//public class WebSocketHandlerImpl implements WebSocketHandler {
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
//        Logger.green_write("afterConnectionEstablished");
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
//        Logger.green_write("handleMessage");
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
//        Logger.green_write("handleTransportError");
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
//        Logger.green_write("afterConnectionClosed");
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//}

import app.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketHandlerImpl extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
//        Message messageFromJson = objectMapper.readValue(messageText, Message.class);

        session.sendMessage(message);

        Logger.green_write("HandleMessage method");
        Logger.green_write(messageText);

//        if (!sessions.containsKey(messageFromJson.getFrom())) {
//            sessions.put(messageFromJson.getFrom(), session);
//        }
//
//        for (WebSocketSession currentSession : sessions.values()) {
//            currentSession.sendMessage(message);
//        }
    }
}
