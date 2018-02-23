package ui.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class testStage extends Stage {
    public testStage(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("viewer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Group group = new Group(root);
        this.setScene(new Scene(root,  500, 650, Color.YELLOW));
    }
}
