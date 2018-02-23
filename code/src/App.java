import javafx.application.Application;
import javafx.stage.Stage;
import ui.control.ControlStage;

public class App extends Application  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage = new ControlStage();
        primaryStage.show();

    }
}
