package ui.component;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ServoSliderEx extends Parent{

    private JFXSlider slider;
    private Label Name;
    private Label value;

    public ServoSliderEx(String name){
        BorderPane grid = new BorderPane();

        slider = new JFXSlider(0,180,60);
        slider.setIndicatorPosition(JFXSlider.IndicatorPosition.LEFT);
        slider.setMinWidth(500);

        Name = new Label(name);

        value = new Label(String.valueOf(slider.getValue()));

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {

                value.setText(newValue.toString());
            }
        });


        grid.setTop(Name);
        grid.setBottom(slider);
        grid.setRight(value);

        this.getChildren().add(grid);
    }

    public double getServoValue(){
        return this.slider.getValue();
    }

    public void setServoValue(double d){
        this.slider.setValue(d);
    }

}
