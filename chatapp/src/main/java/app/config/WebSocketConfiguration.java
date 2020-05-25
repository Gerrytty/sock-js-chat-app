package app.config;

import app.handler.WebSocketHandlerImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@ComponentScan("app.handler")
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final WebSocketHandlerImpl handler;

    public WebSocketConfiguration(WebSocketHandlerImpl handler) {
        this.handler = handler;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/ws").withSockJS();
    }
}
