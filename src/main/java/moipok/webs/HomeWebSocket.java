package moipok.webs;

import moipok.models.Cube;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HomeWebSocket extends TextWebSocketHandler {
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                super.afterConnectionEstablished(session);
        }

        @Override
        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                super.handleMessage(session, message);
        }



        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


                session.sendMessage(message);

        }


}
