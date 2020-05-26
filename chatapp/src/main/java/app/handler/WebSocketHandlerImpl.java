package app.handler;

import app.Logger;
import app.dto.ChatDto;
import app.dto.MessageDto;
import app.models.Message;
import app.repositories.MessagesRepository;
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

    @Autowired
    private MessagesRepository messagesRepository;

    private static final Map<ChatDto, WebSocketSession> sessions = new HashMap<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        Logger.green_write("HandleMessage method");

        String messageText = (String) message.getPayload();
        MessageDto messageFromJson = objectMapper.readValue(messageText, MessageDto.class);
        ChatDto chatDto = new ChatDto(messageFromJson.getFrom(), messageFromJson.getRoom());

        if(messageFromJson.getText().equals("Login")) {

            sessions.put(chatDto, session);

        }

        else {

            session.sendMessage(message);
            Logger.green_write(messageFromJson.toString());

            if (!sessions.containsKey(chatDto)) {
                sessions.put(chatDto, session);
            }

            for (Map.Entry<ChatDto, WebSocketSession> entry : sessions.entrySet()) {

                if(entry.getKey().getRoom().equals(messageFromJson.getRoom()) &&
                !entry.getKey().getFrom().equals(messageFromJson.getFrom())) {
                    entry.getValue().sendMessage(message);
                    messagesRepository.save(Message.builder()
                            .roomId(Long.parseLong(messageFromJson.getRoom()))
                            .userId(Long.parseLong(messageFromJson.getFrom()))
                            .text(messageFromJson.getText())
                            .build());
                }

            }

        }

        Logger.green_write("Size of sessions map = " + sessions.size());

    }
}
