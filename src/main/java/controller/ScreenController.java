package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ScreenController {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DataScreen{
        private Pane pane;
        private FXMLLoader loader;
    }

    private static HashMap<String, DataScreen> screenMap = new HashMap<>();

    public static void set() throws IOException {
        ScreenController.addScreen("main", ScreenController.setDataScreen("Main.fxml", "css/style.css"));
        ScreenController.addScreen("room", ScreenController.setDataScreen("JoinRoom.fxml", "css/style.css"));
    }

    public static DataScreen setDataScreen(String ressouces, String styleFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ressouces)));
        Pane pane = loader.load();
        pane.getStylesheets().add(styleFile);

        return new DataScreen(pane, loader);
    }

    public static Object getController(String name){
        return screenMap.get(name).getLoader().getController();
    }

    public static void addScreen(String name, DataScreen dataScreen){
        screenMap.put(name, dataScreen);
    }

    public static Scene activate(String name){
       return new Scene(screenMap.get(name).getPane());
    }
}

