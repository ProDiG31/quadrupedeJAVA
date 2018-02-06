package ui.presenter;

import com.quadru.SerialReader;
import com.quadru.SerialWriter;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import ui.component.LabeledSlider;
import ui.view.ConnectView;
import ui.view.DashboardView;
import ui.view.View;

import javax.swing.*;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectPresenter extends Presenter<ConnectPresenter.Displayable> {

    public static final String NAME = "connection";

    public interface Displayable extends View {
        JComboBox getPortSelect();
        JButton getRefresh();
        JButton getConnect();
    }

    public ConnectPresenter(ConnectPresenter.Displayable view) {
        super(view);
    }

    @Override
    public void execute() {
        getView().getConnect().addActionListener(e -> {
            try {
                connect(getView().getPortSelect().getSelectedItem().toString());
            } catch (Exception e1) {
                System.out.println("Error append");
                e1.printStackTrace();
            }
           goTo(DashboardPresenter.NAME);
        });

        getView().getRefresh().addActionListener(e -> {
            getView().getComponent().repaint();
        });

    }

    @Override
    public String getPath() {
        return NAME;
    }

    void connect(String portName) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        // Check si le port identifié est deja utilisé
        if ( portIdentifier.isCurrentlyOwned()) {

            System.out.println("Error: Port is currently in use");

        } else {
            // Creation de l'instance de port a partir de son identifier
            setPort(portIdentifier.getName());
            System.out.println("Port selectionné : "+ getPort());

            CommPort commPort = portIdentifier.open("open Comm",2000);
            System.out.println("-- Ouverture de la connection --");

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                (new Thread(new SerialWriter(out))).start();

                serialPort.addEventListener(new SerialReader(in));
                serialPort.notifyOnDataAvailable(true);

            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

}
