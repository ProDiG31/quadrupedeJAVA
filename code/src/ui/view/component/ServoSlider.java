package ui.view.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ServoSlider {

    @FXML
    public VBox servo_Container;

    @FXML
    public Label servo_Label;

    @FXML
    public Slider servo_Slider;

    @FXML
    public TextField servo_Value;

    public void initialize(){


        servo_Slider.setShowTickLabels(true);
        servo_Slider.setMax(180);
        servo_Slider.setMin(0);
        servo_Slider.setShowTickMarks(true);

        servo_Slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                servo_Value.setText(""+newValue.intValue());
            }
        });
    }

    public void setServo_Slider(double x){
        servo_Slider.setValue(x);
    }

    public void setLabel(int i ){
        String member = "";

        if (i % 2 == 0) member = "Leg";
        else member = "Foot";

        servo_Label.setText(member+'_'+(i/2));

    }
}
