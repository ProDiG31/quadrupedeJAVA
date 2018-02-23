package ui.control;

import gnu.io.SerialPort;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.view.StageConnect;
import ui.view.StageDashboard;

import java.util.ArrayList;
import java.util.List;

public class ControlStage extends Stage {

    private List<Scene> views;

    public ControlStage(){
        this.setTitle("Quadrupede Controller");
        this.views = new ArrayList<>();
        this.setScene(new Scene(new StageConnect(),  500, 650, Color.YELLOW));
    }

    public void swapDashboard(SerialPort portArduino){
        this.setScene(new Scene(new StageDashboard(portArduino),  800, 850, (Color.web("#293133"))));
        this.setFullScreen(true);
    }


}
