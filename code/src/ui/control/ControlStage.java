package ui.control;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.lwjgl.Version;
import ui.view.StageConnect;
import ui.view.StageDashboard;

import java.util.ArrayList;
import java.util.List;

public class ControlStage extends Stage {

    private List<Scene> views;

    public ControlStage(){
        this.setTitle("Quadrupede Controller" + Version.getVersion());

        this.views = new ArrayList<>();

        this.loadScene();
//        this.setListener();

    }

    private void loadScene(){

        Scene scene = new Scene(new StageConnect(),  500, 650, Color.WHITE);
        Scene dashboard = new Scene(new StageDashboard(),  1200, 850, Color.WHITE);

        this.views.add(scene);
        this.views.add(dashboard);
        this.setScene(dashboard);
    }

    private void setListener(){

    }
}
