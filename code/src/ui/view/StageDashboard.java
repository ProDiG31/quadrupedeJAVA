package ui.view;

import com.quadru.SerialReader;
import com.quadru.SerialWriter;
import gnu.io.SerialPort;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.component.Viewers3d;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

public class StageDashboard extends Parent {

    //private ServoSliderEx[] servo_slider;
    //private Viewers3d quadru_3d_views;
    private SerialPort commArduino;

    public StageDashboard(SerialPort commArduino){
        this.commArduino = commArduino;
        this.set3dView();
        this.setDashboard();
    }

    public void setDashboard(){
        setServo();
        setDialog();
    }

    public void set3dView(){
        Viewers3d view3d = new Viewers3d();
        //view3d.getSubcene().
        view3d.getSubcene().setTranslateX(800);
        view3d.getSubcene().setTranslateY(10);
        view3d.getSubcene().setTranslateZ(2);
        this.getChildren().add(view3d.getSubcene());
    }

    private void setServo(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/ui/view/component/ServoDashboard.fxml"));
        AnchorPane sliderDashboard = null;
        try {
            sliderDashboard = loader.load();
            sliderDashboard.setLayoutX(50);
            sliderDashboard.setLayoutY(20);
        } catch (IOException e){
            e.printStackTrace();
        }
        sliderDashboard.setTranslateZ(10);
        this.getChildren().add(sliderDashboard);
    }

    private void setDialog(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/ui/view/component/readerStream.fxml"));
        AnchorPane gridDialog = null;

        try {
            gridDialog = loader.load();
            gridDialog.setLayoutX(50);
            gridDialog.setLayoutY(450);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getChildren().add(gridDialog);
    }

    private void setThread() throws IOException, TooManyListenersException {

        System.out.println("-- Connection établie --");

        InputStream in = commArduino.getInputStream();
        OutputStream out = commArduino.getOutputStream();

        System.out.println("-- Streams I/O ouverts --");

        System.out.println("-- start thread --");

        (new Thread(new SerialWriter(out))).start();

        System.out.println("-- Setting listener on SerialPort --");

        commArduino.addEventListener(new SerialReader(in));
        commArduino.notifyOnDataAvailable(true);

        //button.setOnAction((action)->dialog.show(rootStackPane));

    }
}
