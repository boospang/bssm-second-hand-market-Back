package usedmarket.usedmarket.domain.chat.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import usedmarket.usedmarket.domain.chat.presentation.ChatMessage;
import usedmarket.usedmarket.domain.chat.service.ChatService;

import java.util.HashSet;
import java.util.Set;


@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        chatService.saveMessage(chatMessage.getMessage());
        sendMessage(chatMessage, chatService);
    }

    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }
}
