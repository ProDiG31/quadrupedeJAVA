package ui.view;

import ui.component.LabeledSlider;
import ui.presenter.DashboardPresenter;

import javax.swing.*;
import java.awt.*;

public class DashboardView implements DashboardPresenter.Displayable {

    private LabeledSlider[] servosliders;
    private static final int NB_SERVO = 8;

    public DashboardView() {
        servosliders = new LabeledSlider[NB_SERVO];
        for (int i = 0; i < NB_SERVO ; i++){
            this.servosliders[i] = new LabeledSlider("Servo"+(i+1)/2+"_"+(i+1)%2);
        }
    }

    @Override
    public Component getComponent() {
        DefaultButtonModel model = new DefaultButtonModel();
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalGlue());
        for (LabeledSlider slid : servosliders){
            verticalBox.add(slid);
            verticalBox.add(Box.createVerticalGlue());
        }
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(verticalBox);
        return panel;
    }

    @Override
    public LabeledSlider[] getServoSliders() {
        return this.servosliders;
    }
}
