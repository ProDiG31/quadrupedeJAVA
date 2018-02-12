package ui.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.quadru.Arduino;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.quadru.Arduino.connect;

public class StageConnect extends Parent{

    public VBox grid;
    public JFXButton btnConnect;
    public JFXButton btnRefresh;
    public Label messager;
    public JFXComboBox<String> portSelect;
    public JFXComboBox<Integer> baudRateSelect;

    public StageConnect(){
        grid = new VBox();
//        Group group = new Group();
//        Scene scene = new Scene(group, 300, 250, Color.BLUE);

            //Refresh Button
        btnRefresh = new JFXButton("REFRESH");
        Image imageRefresh = new Image(getClass().getResourceAsStream("/ressources/images/Refresh_Icon.png"),
                40F,
                40F,
                true,
                true);
        btnRefresh.setGraphic(new ImageView(imageRefresh));

            //COM Port Selector
        portSelect = new JFXComboBox<>();
        portSelect.setMaxSize(200F,120F);
        portSelect.setPromptText("Select your COM port");

            //BaudRate Selector
        Integer[] baudRateComboBoxValue = {9600, 19200, 38400, 57600, 115200, 230400, 250000};
        baudRateSelect = new JFXComboBox<Integer>();
        baudRateSelect.getItems().addAll(baudRateComboBoxValue);
        baudRateSelect.setMaxSize(200F,120F);
        baudRateSelect .setPromptText("Select your BaudRate");

            //Connection Button
        btnConnect = new JFXButton("Connection");

        Image imageConnect = new Image(getClass().getResourceAsStream("/ressources/images/Connect_Icon.png"),
                40F,
                40F,
                true,
                true);
        btnConnect.setGraphic(new ImageView(imageConnect));

        messager = new Label();
        messager.setMaxSize(200F,120F);

        grid.setSpacing(25F);
        grid.getChildren().add(btnRefresh);
        grid.getChildren().add(portSelect);
        grid.getChildren().add(baudRateSelect);
        grid.getChildren().add(btnConnect);
        grid.getChildren().add(messager);

        btnRefresh.setOnAction(event -> portSelect.getItems().addAll(Arduino.getPortEnableList(messager)));

        btnConnect.setOnAction(event -> {
            if (portSelect.getValue() != null && !portSelect.getValue().isEmpty()) {
                if (baudRateSelect.getValue() != null && !baudRateSelect.getValue().toString().isEmpty()){
                    try {
                        if (connect(portSelect.getValue(), baudRateSelect.getValue(), messager)){
                            //Redirect on Dashboard
                            messager.setTextFill(Color.GREEN);
//                            this.getParent().add();
//                            this.getParent().getChildrenUnmodifiable().add()
                        } else {
                            messager.setTextFill(Color.RED);
                        };
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Connection Error : baudRate");
                    alert.setHeaderText(null);
                    alert.setContentText("No baudRate Selected");

                    alert.showAndWait();

                    messager.setTextFill(Color.RED);
                    messager.setText("Select boardRate value");
                }

            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Connection Error : COM");
                alert.setHeaderText(null);
                alert.setContentText("No port COM Selected");

                alert.showAndWait();

                messager.setTextFill(Color.RED);
                messager.setText("Select port COM");
            }

        });

        this.getChildren().add(grid);
        this.setTranslateX(100);
        this.setTranslateY(30);

    }

}
