package ui.view;

import gnu.io.CommPortIdentifier;
import ui.presenter.ConnectPresenter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;



public class ConnectView implements ConnectPresenter.Displayable{
    JLabel txt1 = new JLabel("Selectionner le port : ");
    ArrayList<String> portEnable; // = {"COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9"};
    JComboBox portSelec; //= new JComboBox(portEnable);
    JButton refresh;
    JButton connect;

    public ConnectView(){

        CommPortIdentifier serialPort;
        Enumeration enumComm;

        refresh = new JButton("Refresh");
        connect = new JButton("Connect");

        // detection de tout les port utilisables
        System.out.println("Detection des ports utilisables : ");
        enumComm = CommPortIdentifier.getPortIdentifiers();
        portEnable = new ArrayList<String>();

        while (enumComm.hasMoreElements()){
            serialPort = (CommPortIdentifier) enumComm.nextElement();
            System.out.println("checking port "+serialPort.getName());
            if(serialPort.getPortType() == CommPortIdentifier.PORT_SERIAL){
                portEnable.add(serialPort.getName());
                System.out.println("Serial Port find : "+serialPort.getName());
            }
        }

        //JCombox pour selectionner le port de connection
        portSelec = new JComboBox(portEnable.toArray());
    }


    @Override
    public Component getComponent() {
        DefaultButtonModel model = new DefaultButtonModel();
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(refresh);
        verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(portSelec);
        verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(connect);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(verticalBox);
        return panel;
    }

    @Override
    public JComboBox getPortSelect(){
        return this.portSelec;
    }

    @Override
    public  JButton getRefresh(){
        return this.refresh;
    }

    @Override
    public JButton getConnect(){
        return this.connect;
    }
}
