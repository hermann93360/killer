package service;

import dto.ChatDto;
import dto.Dto;
import dto.RoomDto;
import javafx.collections.ObservableList;
import transfert.Transfer;

import java.io.IOException;
import java.security.Provider;

import static transfert.Transfer.Type.REQUEST;

public class ChatService extends ManageServerService{

    public ChatService() {
    }

    public static ChatService init(){
        return new ChatService();
    }

    // request

    public void sendChat(String text) throws IOException {
        Transfer transfer = new Transfer("/user/send/public/chat", new ChatDto(text), REQUEST, super.getIdServer());
        super.sendToServer(transfer);
    }


    // Notification

    public void newPublicChat(Dto dto, ObservableList<ChatDto> chatDtoObservableList) {
        ChatDto chatDto = (ChatDto) dto;
        chatDtoObservableList.add(chatDto);
    }
}
