package ui.view.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ServoDashboard {

    @FXML
    private HBox servo_Container;

    public void initialize(){

        for (int i = 0; i < 8; i++){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("ServoSlider.fxml"));
                servo_Container.getChildren().add(loader.load());
                ServoSlider controlleur = loader.<ServoSlider>getController();
                controlleur.setLabel(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
