import ui.presenter.DashboardPresenter;
import ui.service.Dispatcher;
import ui.view.DashboardView;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Application {
    public static void main(String[] args) {
        invokeLater(Application::run);
    }

    private static void run() {

        System.out.println("-- Start Programm --");

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dispatcher dispatcher = new Dispatcher(jFrame);
        dispatcher.registerPresenter(new DashboardPresenter(new DashboardView()));
        //dispatcher.registerPresenter(new ConnectPresenter(new ConnectView()));
        jFrame.setVisible(true);
        //jFrame.setName(ConnectPresenter.NAME);
        jFrame.setSize(800, 800);

        System.out.println("-- End Programm --");
    }
}