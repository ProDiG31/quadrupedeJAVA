package ui.component;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends JPanel{

    private JLabel title;
    private JTextField input;
    private JLabel errorMessage;

    public LabeledTextField(String libelle){
        this.setLayout(new GridLayout(3,1));

        title = new JLabel(libelle);
        input = new JTextField();
        errorMessage = new JLabel();
        errorMessage.setForeground(Color.red);

        add(title);
        add(input);
        add(errorMessage);
    }

    public String getInputValue() {
        return input.getText();
    }

    public void setErrorMessage(String errorOut){
        this.errorMessage.setText(errorOut);
    }

    public void reset(){
        this.input.setText("");
        this.errorMessage.setText("");
    }
}
