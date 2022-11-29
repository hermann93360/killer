package controller;

import dto.RoomDto;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.Value;
import service.MainService;
import service.RoomService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

public class MainController extends Controller {

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputAddRoom;

    @FXML
    private Pane screenSetName;

    @FXML
    private Pane screenMain;

    @FXML
    private Pane screenJoinRoom;

    @FXML
    private TilePane boxRoom;

    private MainService mainService;

    private RoomService roomService;

    @FXML
    public void logServer() throws IOException {
        this.mainService.start();

        this.screenMain.setVisible(false);
        this.screenSetName.setVisible(true);
    }

    @FXML
    public void setNameUser() {
        try {

            this.mainService.setNameUser(inputName.getText());
            this.screenJoinRoom.setVisible(true);
            this.screenSetName.setVisible(false);


        } catch (Exception e) {
            //TODO traitement
        }
    }

    @FXML
    public void addRoom() throws IOException {
        this.roomService.addRoom(inputAddRoom.getText());
    }

    public void displayRooms(List<RoomDto> list) {
    }

    public void displayNewRoom(RoomDto room){
        Platform.runLater(() -> {
            VBox btnRoom = new VBox();
            btnRoom.setId("btnRoom");

            Label btnRoomText = new Label(room.getName());
            btnRoomText.setId("btnRoomText");

            Label capacityRoom = new Label(room.getNumberOfUser() + "  /  6");
            capacityRoom.setId("capacityRoom");

            Label id = new Label(String.valueOf(room.getId()));
            id.setId("id");
            id.setVisible(false);

            btnRoom.getChildren().add(id);
            btnRoom.getChildren().add(btnRoomText);
            btnRoom.getChildren().add(capacityRoom);


            btnRoom.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                try {
                    this.roomService.joinRoom(room.getId());
                    super.changeScreen("room");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            this.boxRoom.getChildren().add(btnRoom);
        });
    }

    public void updateRoom(long idRoom, int capacity){

        VBox vBox = (VBox)this.boxRoom.getChildren()
                .stream()
                .filter(x -> ( ((Label)((VBox) x).getChildren().get(0))).getText().equals(String.valueOf(idRoom)))
                .findAny()
                .get();

        Platform.runLater(() -> {
            ((Label) vBox.getChildren().get(2)).setText(capacity + "  /  6");
        });

    }


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mainService = MainService.init();
        this.roomService = RoomService.init();
    }

}
