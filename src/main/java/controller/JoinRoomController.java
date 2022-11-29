package controller;

import dto.UserDto;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import service.RoomService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JoinRoomController extends Controller{

    private RoomService roomService;

    public void displayUsersInRoom(List<UserDto> list) {
        Platform.runLater(() -> {
            super.getCentral().getChildren().clear();
            list.forEach(user -> super.getCentral().getChildren().add(new Text(user.getName())));
        });
    }

    //test

    public void toNight(){
        super.getCentral().setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    public void toDay(){
        super.getCentral().setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomService = RoomService.init();
    }
}
