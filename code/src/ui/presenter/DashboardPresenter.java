package ui.presenter;
import ui.component.LabeledSlider;
import ui.view.View;

public class DashboardPresenter extends Presenter<DashboardPresenter.Displayable> {

    public static final String NAME = "dashboard";

    public interface Displayable extends View {
        LabeledSlider[] getServoSliders();
    }

    public DashboardPresenter(Displayable view) {
        super(view);
    }

    @Override
    public void execute() {
       //getView().getServoSliders();
    }

    @Override
    public String getPath() {
        return NAME;
    }
}
