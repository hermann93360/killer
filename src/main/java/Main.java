import controller.RoomController;
import controller.ScreenController;
import dto.ChatDto;
import dto.DiscussionDto;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import static controller.ScreenController.getController;
import static javafx.collections.FXCollections.observableArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(ScreenController.activate("main"));
        primaryStage.setResizable(false);
        primaryStage.setWidth(740);
        primaryStage.setHeight(560);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {

            EventHandler<MouseEvent> mouseDragged = e -> {
                primaryStage.setX(e.getScreenX() - (primaryStage.getWidth() - (primaryStage.getWidth() - event.getX())));
                primaryStage.setY(e.getScreenY() - (primaryStage.getHeight() - (primaryStage.getHeight() - event.getY())));
            };

            if(event.getX() < 9 || event.getY() < 9 || event.getX() > primaryStage.getWidth() - 9 || event.getY() > primaryStage.getHeight() - 9 ){
                primaryStage.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDragged);
            }

            primaryStage.addEventHandler(MouseEvent.MOUSE_RELEASED, (released) -> {
                primaryStage.removeEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDragged);
            });
        });

        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        ScreenController.set();
        launch(args);

         /*
        ObservableList<DiscussionDto> test = observableArrayList();
        test.add(new DiscussionDto(new Stack<>(), "Hermann", "Manon"));
        test.add(new DiscussionDto(new Stack<>(), "Anais", "Leandra"));


        test.addListener((ListChangeListener<DiscussionDto>) change -> {
            while (change.next()) {
                if (change.wasAdded() && !change.wasReplaced()) {
                }
                if (change.wasAdded() && change.wasReplaced()) {
                    change.getAddedSubList().forEach(x -> System.out.println(x.getListChat().peek()));
                }
            }
        });

        DiscussionDto discussionDto = test.get(0);
        discussionDto.getListChat().push(new ChatDto("Hermann", "Salut"));
        discussionDto.getListChat().push(new ChatDto("Anais", "qoi"));
        discussionDto.getListChat().push(new ChatDto("Hermann", "Salut"));
        test.set(0, discussionDto);


          */

    }

    public DiscussionDto newChat(ChatDto chatDto, ObservableList<DiscussionDto> discussionDtos){
        /*
        DiscussionDto d = discussionDtos
                .stream()
                .filter(x -> x.getId() == chatDto.getIdDiscussion())
                .findAny()
                .get();

        int index = discussionDtos.indexOf(d);
        d.getListChat().push(chatDto);

        discussionDtos.set(index, d);

         */

        return null;
    }

}