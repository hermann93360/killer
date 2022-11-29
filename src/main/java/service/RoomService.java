package service;

import controller.JoinRoomController;
import controller.MainController;
import controller.RoomController;
import dto.*;
import javafx.collections.ObservableList;
import transfert.Transfer;

import java.io.IOException;

import static controller.ScreenController.getController;
import static transfert.Transfer.Type.*;

public class RoomService extends ManageServerService {


    public RoomService(){

    }
    public static RoomService init() {
        return new RoomService();
    }

    public void addRoom(String name) throws IOException {
        Transfer transfer = new Transfer("/add/room", new RoomDto(name), REQUEST, super.getIdServer());
        super.sendToServer(transfer);
    }

    public void joinRoom(long id) throws IOException {
        Transfer transfer = new Transfer("/join/room", new RoomDto(id), REQUEST, super.getIdServer());
        super.sendToServer(transfer);
    }

    public void getRooms() throws IOException {
        Transfer transfer = new Transfer("/get/rooms", null, REQUEST, super.getIdServer());
        super.sendToServer(transfer);
    }

    public void getUserInRoom(long id) throws IOException {
        Transfer transfer = new Transfer("/get/users/room", new RoomDto(id), REQUEST, super.getIdServer());
        super.sendToServer(transfer);
    }

    // Response

    public void getRoomsResponse(Dto dto) {

        RoomsListDto roomsListDto = (RoomsListDto) dto;

        ((MainController)getController("main"))
                .displayRooms(roomsListDto.getList());
    }

    public void getUsersInRoomResponse(Dto dto) {

        UsersListDto usersListDto = (UsersListDto) dto;

        ((JoinRoomController)getController("joinRoom"))
                .displayUsersInRoom(usersListDto.getList());
    }


    // Notification

    public void setUserInRoom(Dto dto, ObservableList<UserDto> user){
        UsersListDto usersListDto = (UsersListDto) dto;
        user.addAll(usersListDto.getList());
    }
    public void someoneJoinRoomNotification(Dto dto, ObservableList<UserDto> users) throws IOException {
        System.out.println(" -> NOTIF : someone join room <- \n");

        UserDto userDto = (UserDto) dto;
        users.add(userDto);
    }

    public void someoneJoinRoomAllNotification(Dto dto, ObservableList<RoomDto> observableList) {
        RoomDto roomDto = (RoomDto) dto;

        int index = observableList.indexOf
                (observableList.stream()
                .filter(x -> x.getId() == roomDto.getId())
                .findAny()
                .get()
        );

        observableList.set(index, roomDto);

    }

    public void newRoomNotification(Dto dto, ObservableList<RoomDto> observableList) throws IOException {
        System.out.println(" -> NOTIF : new room <-");

        RoomDto roomDto = (RoomDto) dto;
        observableList.add(roomDto);
    }

    public void putDayNotification() {
        ((JoinRoomController)getController("joinRoom"))
                .toDay();
    }

    public void putNightNotification() {
        ((JoinRoomController)getController("joinRoom"))
                .toNight();
    }

    public void setRoomNotification(Dto dto, ObservableList<RoomDto> room) {
        RoomsListDto roomsListDto = (RoomsListDto) dto;
        room.addAll(roomsListDto.getList());
    }

    public void setPlayerImg(Dto dto) {
        RoomDto roomDto = (RoomDto) dto;
        ((RoomController)getController("room"))
                .setPathPlayerImg(roomDto.getPathAllIconsOfUser());
    }
}
