package com.quadru;


import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

public class Arduino {

    private static CommPortIdentifier serialPort;
    private static Enumeration enumComm;
    private static ArrayList<String> portEnable;

    private static String port;

    public static ArrayList<String> getPortEnableList(Label messager){
        // detection de tout les port utilisables
        System.out.println("Detection des ports utilisables : ");
        messager.setText("Detection des ports utilisables en cours");
        enumComm = CommPortIdentifier.getPortIdentifiers();
        portEnable = new ArrayList<String>();

        while (enumComm.hasMoreElements()){
            serialPort = (CommPortIdentifier) enumComm.nextElement();
            System.out.println("checking port "+serialPort.getName());
            if(serialPort.getPortType() == CommPortIdentifier.PORT_SERIAL){
                portEnable.add(serialPort.getName());
                System.out.println("Serial Port find : "+serialPort.getName());
                messager.setText("Serial Port find : "+serialPort.getName());
            }
        }
        return portEnable;
    }


    public static boolean connect(String portName, int baudrate, Label messager) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        // Check si le port identifié est deja utilisé
        if ( portIdentifier.isCurrentlyOwned()) {

            messager.setTextFill(Color.RED);
            messager.setText("Error: Port is currently in use");
            return false;
        } else {

            messager.setTextFill(Color.ORANGE);
            // Creation de l'instance de port a partir de son identifier
            setPort(portIdentifier.getName());
            System.out.println("Port selectionné : "+ getPort());
            messager.setText("Port selectionné : "+ getPort());

            CommPort commPort = portIdentifier.open("open Comm",2000);
            System.out.println("-- Ouverture de la connection --");
            messager.setText("-- Ouverture de la connection --");

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams( baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                messager.setTextFill(Color.GREEN);

                (new Thread(new SerialWriter(out))).start();

                serialPort.addEventListener(new SerialReader(in));
                serialPort.notifyOnDataAvailable(true);

                return true;

            } else {
                messager.setTextFill(Color.RED);
                System.out.println("Error: Only serial ports are handled by this software.");
                messager.setText("Error: Only serial ports are handled by this software");
                return false;
            }
        }
    }

    public static String getPort(){
        return port;
    }


    public static void setPort(String portNew){
        port = portNew;
    }
}
