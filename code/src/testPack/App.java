package testPack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        String resourcePath = "viewer.fxml";
        URL location = getClass().getResource(resourcePath);
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        Scene scene = new Scene(fxmlLoader.load(), 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
