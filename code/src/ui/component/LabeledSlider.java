package ui.component;

import javafx.scene.control.Slider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class LabeledSlider extends JPanel{

    static final int FPS_MIN = 0;
    static final int FPS_MAX = 180;

    private JLabel title;
    private JSlider slider;
    private JLabel value;

    public LabeledSlider(String libelle){
        this.setLayout(new GridLayout(1,3));

        char servoType = libelle.charAt(libelle.length()-1);
        int fps_init;

        //Case servo 1 => Feet
        if(servoType == '1'){
            fps_init = 180;
        } else { //Case servo 2 => leg
            fps_init = 45;
        }

        title = new JLabel(libelle);
        slider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, fps_init);
        value = new JLabel(String.valueOf(slider.getValue()));

        slider.addChangeListener(new updater(this));
        add(title);
        add(slider);
        add(value);
    }

    private class  updater implements ChangeListener{

        private LabeledSlider labeledSliderListening;

        private updater(LabeledSlider labeledSlider){
            this.labeledSliderListening = labeledSlider;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slid = (JSlider) e.getSource();
            this.labeledSliderListening.setText((int)slid.getValue());
        }
    }

    public int getInputValue() {
        return slider.getValue();
    }

    public void setValue(int n){
        slider.setValue(n);
    }

    public void setText(int n){
        this.value.setText(String.valueOf(n));
    }

}
