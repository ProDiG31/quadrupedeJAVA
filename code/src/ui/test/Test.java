package test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.view.StageConnect;

public class Test extends Application {

    public static void main(String[] args) {
        Application.launch(Test.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quadrupede Controller");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 650, Color.GRAY);

        root.getChildren().add(new StageConnect());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
