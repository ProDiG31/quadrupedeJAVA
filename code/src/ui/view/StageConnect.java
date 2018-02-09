package ui.view;

import com.quadru.Arduino;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.quadru.Arduino.connect;

public class StageConnect extends Parent{

    public VBox grid;
    public Button btnConnect;
    public Button btnRefresh;
    public Label messager;
    public ComboBox<String> portSelect;

    public StageConnect(){
        grid = new VBox();
        Group group = new Group();
        Scene scene = new Scene(group, 300, 250, Color.GRAY);

        btnRefresh = new Button();
        btnRefresh.setMaxSize(200F,120F);
        btnRefresh.setText("Refesh");
        Image imageRefresh = new Image(getClass().getResourceAsStream("/ressources/images/Refresh_Icon.png"),
                40F,
                40F,
                true,
                true);

        btnRefresh.setGraphic(new ImageView(imageRefresh));

        portSelect = new ComboBox<String>();
        //portSelect.getItems().add("Start Refresh"); //.addAll(Arduino.getPortEnableList());
        portSelect.setMaxSize(200F,120F);

        btnConnect = new Button();
        btnConnect.setMaxSize(200F,120F);
        btnConnect.setText("Connect");
        Image imageConnect = new Image(getClass().getResourceAsStream("/ressources/images/Connect_Icon.png"),
                40F,
                40F,
                true,
                true);
        btnConnect.setGraphic(new ImageView(imageConnect));

        messager = new Label();
        messager.setMaxSize(200F,120F);

        grid.setSpacing(15F);
        grid.getChildren().add(btnRefresh);
        grid.getChildren().add(portSelect);
        grid.getChildren().add(btnConnect);
        grid.getChildren().add(messager);

        btnRefresh.setOnAction(event -> portSelect.getItems().addAll(Arduino.getPortEnableList(messager)));
        btnConnect.setOnAction(event -> {
            try {
                String com = portSelect.getValue();
                messager.setText("Connecting on " + com);
                connect(com, messager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.getChildren().add(grid);
        this.setTranslateX(100);
        this.setTranslateY(30);

    }

}
