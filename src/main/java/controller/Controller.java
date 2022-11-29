package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public abstract class Controller implements Initializable {

    @FXML
    private Pane central;

    protected void changeScreen(String screen){
        Stage stage = (Stage) this.central.getScene().getWindow();
        stage.setScene(ScreenController.activate(screen));
    }

}
