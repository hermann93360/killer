package controller;

import dto.ChatDto;
import dto.UserDto;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import service.ChatService;
import service.RoomService;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RoomController extends Controller {


    private RoomService roomService;

    private ChatService chatService;

    @FXML
    private Label playeur1;

    @FXML
    private Label playeur2;

    @FXML
    private Label playeur3;

    @FXML
    private Label playeur4;

    @FXML
    private Label playeur5;

    @FXML
    private Label playeur6;

    @FXML
    public VBox playeurBox1;

    @FXML
    public VBox playeurBox2;

    @FXML
    public VBox playeurBox3;

    @FXML
    public VBox playeurBox4;

    @FXML
    public VBox playeurBox5;

    @FXML
    public VBox playeurBox6;

    public List<VBox> playerBox;

    private List<Label> playeursName;

    private List<Image> iconPlayeurs;


    // deplace in chatController

    @FXML
    public VBox centralChatsBox;

    @FXML
    public TextField inputChat;

    @FXML
    public VBox scrollChats;

    @FXML
    public ScrollPane scrollBox;

    public void displayNewUserInRoom(UserDto user, long idCurrentUser) {
        Platform.runLater(() -> {
            for (VBox pB : playerBox) {
                if (((Label) pB.getChildren().get(2)).getText().equals(user.getPathPlayerImg())) {
                    ((Label) pB.getChildren().get(1)).setText(user.getName());
                    if(user.getIdentifier() == idCurrentUser) {
                        if(!pB.getId().equals(playeurBox5.getId())){
                            List<Node> p5 = new ArrayList<>(playeurBox5.getChildren());
                            List<Node> pb = new ArrayList<>(pB.getChildren());

                            playeurBox5.getChildren().clear();
                            playeurBox5.getChildren().addAll(pb);

                            pB.getChildren().clear();
                            pB.getChildren().addAll(p5);
                        }
                    }
                    return;
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        scrollChats.heightProperty().addListener(observable -> scrollBox.setVvalue(1D));

        this.roomService = RoomService.init();
        this.chatService = ChatService.init();
        this.iconPlayeurs = new ArrayList<>();

        playeursName = new ArrayList<>();
        playeursName.add(playeur1);
        playeursName.add(playeur2);
        playeursName.add(playeur3);
        playeursName.add(playeur4);
        playeursName.add(playeur5);
        playeursName.add(playeur6);


        playerBox = Arrays.asList(
                playeurBox1,
                playeurBox2,
                playeurBox3,
                playeurBox4,
                playeurBox5,
                playeurBox6
        );
    }

    public void setPathPlayerImg(List<String> pathAllIconsOfUser) {
        for (String s : pathAllIconsOfUser) {
            iconPlayeurs.add(new Image(s));
        }

        for (VBox pB : playerBox) {
            Platform.runLater(() -> {
                pB.getChildren().add(new ImageView(iconPlayeurs.get(playerBox.indexOf(pB))));

                Label labelName = playeursName.get(playerBox.indexOf(pB));
                labelName.setStyle("-fx-text-fill: #ff0000");
                labelName.setAlignment(Pos.CENTER);
                pB.getChildren().add(labelName);

                Label idVBox = new Label(pathAllIconsOfUser.get(playerBox.indexOf(pB)));
                idVBox.setVisible(false);
                pB.getChildren().add(idVBox);

            });
        }
    }


    //deplace in chat controller

    public void displayChatsBox(ActionEvent actionEvent) {
        this.centralChatsBox.setVisible(true);
    }

    @FXML
    public void sendChat() throws IOException {
        this.chatService.sendChat(inputChat.getText());
    }

    public void displayNewChat(ChatDto chat) {
        Platform.runLater(() -> {
            HBox messageBox = new HBox();
            messageBox.setId("messageBox");

            Label name = new Label((chat.getNameSender() + " : ").replace("", " ").toUpperCase());
            name.setId("messageName");

            Label message = new Label(chat.getMessage().replace("", " "));
            message.setWrapText(true);

            messageBox.getChildren().addAll(name, message);

            this.scrollChats.getChildren().add(messageBox);
        });
    }
}
