package ui.component;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import org.lwjgl.system.CallbackI;

public class Viewers3d extends Parent {

//    Scene view_Renderer ;

    private Group part3d;
    private Group root;
    private double sceneX, sceneY=0;
    private double fixedXAngle, fixedYAngle=0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private Scene scene;

    public Viewers3d(){


        Stage primaryStage = new Stage();
        primaryStage.setTitle("SHOW 3D");
        root = new Group();
        scene = new Scene(root, 1024, 800, true);
        scene.setFill(Color.BLACK);


        Camera camera = new PerspectiveCamera();


//        Canvas canvas = new Canvas( 400, 200 );
//        this.getChildren().add( canvas );

        StlMeshImporter stlImporter = new StlMeshImporter();

        try {
            stlImporter.read("leg.stl");
        }
        catch (ImportException e) {
            e.printStackTrace();
            return;
        }

        TriangleMesh mesh = stlImporter.getImport();
        stlImporter.close();
        MeshView meshV = new MeshView(mesh);
        part3d = new Group(meshV);



        root.getChildren().addAll(part3d);

        double max = Math.max(meshV.getBoundsInLocal().getWidth(),
                Math.max(meshV.getBoundsInLocal().getHeight(),
                        meshV.getBoundsInLocal().getDepth()));
        camera.setTranslateZ(-3*max);
        scene.setCamera(camera);

        primaryStage.setScene(scene);
        primaryStage.show();

//        this.getScene().setCamera(camera);
//        this.getScene().setRoot(root);
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        gc.setFill( Color.RED );
//        gc.setStroke( Color.BLACK );
//        gc.setLineWidth(2);
    }


    private void hookupRotate(){
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        root.getTransforms().addAll(xRotate, yRotate);
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();
            fixedXAngle = angleX.get();
            fixedYAngle = angleY.get();
        });

        scene.setOnMouseDragged(event ->{
            angleX.set(fixedXAngle - (sceneX - event.getSceneY()));
            angleY.set(fixedYAngle + (sceneY - event.getSceneX()));
        });

    }

}
