package ui.view;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSlider;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import ui.component.ServoSlider;
import ui.component.Viewers3d;

public class StageDashboard extends Parent {

    private ServoSlider[] servo_slider;
    private Viewers3d quadru_3d_views;

    public StageDashboard(){

        VBox gridServoSlider = new VBox();
        servo_slider = new ServoSlider[8];

        for (int i = 0 ; i < servo_slider.length ; i++){
            ServoSlider slid = new ServoSlider("Servo_"+i);
            slid.setStyle("-fx-border: solid #5B6DCD 10px;");
            servo_slider[i] = slid;

        }

        gridServoSlider.setSpacing(25);
        gridServoSlider.getChildren().addAll(servo_slider);

        gridServoSlider.setLayoutX(50);
        gridServoSlider.setLayoutY(50);

        final String cssDefault = "-fx-border-color: Black;\n"
                + "-fx-border-radius: 30;\n"
                + "-fx-border-width: 4;\n"
                + "-fx-padding: 20;\n";
        gridServoSlider.setStyle(cssDefault);

        VBox grid3Dviewer = new VBox();
        quadru_3d_views = new Viewers3d();
        grid3Dviewer.getChildren().add(quadru_3d_views);

        grid3Dviewer.setLayoutX(800);
        grid3Dviewer.setLayoutY(50);

        grid3Dviewer.setStyle(cssDefault);

        this.getChildren().add(gridServoSlider);
        this.getChildren().add(grid3Dviewer);

    }
}
