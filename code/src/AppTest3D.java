import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.component.Viewers3d;
import ui.control.ControlStage;

public class AppTest3D extends Application  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage = new Stage();
        Viewers3d view3d = new Viewers3d();
        Pane pane = new Pane(view3d.getSubcene());

        //pane.getChildren().add();
        Scene scene = new Scene(pane, 1200,800, Color.BLACK);
        view3d.handleKeyboard(scene);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
