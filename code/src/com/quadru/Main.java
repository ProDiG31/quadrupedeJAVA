package com.quadru;

import gnu.io.*;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;

import static gnu.io.CommPortIdentifier.getPortIdentifiers;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here


        JFrame window = new JFrame();
        JSlider slider = new JSlider();
        slider.setMaximum(1023);
        window.add(slider);
        window.pack();
        window.setVisible(true);

        CommPortIdentifier serialPort;
        Enumeration<CommPortIdentifier> enumComm;

        enumComm = getPortIdentifiers();
        while (enumComm.hasMoreElements()){
            serialPort = enumComm.nextElement();
            if(serialPort.getPortType() == CommPortIdentifier.PORT_SERIAL){
                System.out.println("Serial Port find : "+serialPort.getName());

            }
        }

        System.out.println("-- End Programm --");
    }



}
