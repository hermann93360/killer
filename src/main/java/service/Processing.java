package service;

import controller.MainController;
import controller.RoomController;
import dto.ChatDto;
import dto.Dto;
import dto.RoomDto;
import dto.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import transfert.Transfer;

import java.io.IOException;

import static controller.ScreenController.getController;
import static javafx.collections.FXCollections.observableArrayList;

public class Processing {

    private final RoomService roomService;
    private final MainService mainService;
    private final ChatService chatService;

    private ObservableList<RoomDto> room;
    private ObservableList<UserDto> user;
    private ObservableList<ChatDto> chats;

    private Processing() {
        room = observableArrayList();
        user = observableArrayList();
        chats = observableArrayList();

        roomService = RoomService.init();
        mainService = MainService.init();
        chatService = ChatService.init();

        chats.addListener((ListChangeListener<ChatDto>) change -> {
            while (change.next()) {
                if (change.wasAdded() && !change.wasReplaced()) {
                    change.getAddedSubList()
                            .forEach(value ->
                                    ((RoomController) getController("room"))
                                            .displayNewChat(value)
                            );
                }
            }
        });

        user.addListener((ListChangeListener<UserDto>) change -> {
            while (change.next()) {
                if (change.wasAdded() && !change.wasReplaced()) {
                    change.getAddedSubList()
                            .forEach(value ->
                                    ((RoomController) getController("room"))
                                            .displayNewUserInRoom(value, mainService.getIdServer())
                            );
                }
                if(change.wasAdded() && change.wasReplaced()){

                }
            }
        });

        room.addListener((ListChangeListener<RoomDto>) change -> {
            while (change.next()) {
                if (change.wasAdded() && !change.wasReplaced()) {
                    change.getAddedSubList()
                            .forEach(value ->
                                    ((MainController) getController("main"))
                                            .displayNewRoom(value)
                            );
                }
                if(change.wasAdded() && change.wasReplaced()){

                    RoomDto roomDto = room.get(change.getFrom());

                    ((MainController) getController("main"))
                            .updateRoom(roomDto.getId(), roomDto.getNumberOfUser());

                }
            }
        });


    }

    public static Processing init() {
        return new Processing();
    }

    public void go(Transfer transfer) throws IOException {
        switch (transfer.getType()) {
            case NOTIFICATION:
                this.notification(transfer.getPath(), transfer.getDto());
                break;
            case RESPONSE:
                this.response(transfer.getPath(), transfer.getDto());
                break;
        }
    }

    private void notification(String path, Dto dto) throws IOException {
        switch (path) {
            case "/join/room/notification":
                this.roomService.someoneJoinRoomNotification(dto, user);
                break;
            case "/join/room/all/notification":
                this.roomService.someoneJoinRoomAllNotification(dto, room);
                break;
            case "/new/room/notification":
                this.roomService.newRoomNotification(dto, room);
                break;
            case "init/set/rooms" :
                this.roomService.setRoomNotification(dto, room);
                break;
            case "init/set/users/room" :
                this.roomService.setUserInRoom(dto, user);
                break;
            case "/new/public/chat/notification":
                this.chatService.newPublicChat(dto, chats);
                break;
            case "/play/day":
                this.roomService.putDayNotification();
                break;
            case "/play/night":
                this.roomService.putNightNotification();
                break;
            case "/set/player/img" :
                this.roomService.setPlayerImg(dto);
                break;
        }
    }

    private void response(String path, Dto dto) {
        switch (path) {
            case "/set/id":
                this.mainService.setId(dto);
                break;
            case "/get/rooms":
                this.roomService.getRoomsResponse(dto);
                break;
            case "/get/users/room":
                this.roomService.getUsersInRoomResponse(dto);
                break;
            case "/join/room" :
                this.roomService.setUserInRoom(dto, user);
                break;
        }
    }
}
