package testPack;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import ui.component.quadru3d;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Viewer implements Initializable {

    @FXML
    Group meshGroup;

    private PerspectiveCamera camera;
    private MeshView[] meshView;
    private Group axis;

    private static final int VIEWPORT_SIZE = 500;
    private static final double MODEL_SCALE_FACTOR = 6;
    private static final double MODEL_X_OFFSET = 0;
    private static final double MODEL_Y_OFFSET = 0;
    private static final double MODEL_Z_OFFSET = VIEWPORT_SIZE * 21;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCamera();
        Platform.runLater(() -> showFigure());
    }

    private void initCamera() {
        this.camera = new PerspectiveCamera(true);
        this.camera.setNearClip(0.1);
        this.camera.setFarClip(10000.0);
        this.camera.setTranslateZ(-2000);
    }

    private Group buildScene() {
        Group group = new Group();

        for (MeshView face : meshView) {

            face.setScaleX(MODEL_SCALE_FACTOR);
            face.setScaleY(MODEL_SCALE_FACTOR);
            face.setScaleZ(MODEL_SCALE_FACTOR);
        }
        PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateZ(-2*VIEWPORT_SIZE );
        pointLight.setTranslateY(-2*VIEWPORT_SIZE );

        group.getChildren().addAll(meshView);
        group.getChildren().addAll(pointLight);
        return group;
    }

    private SubScene createScene3D(Group group) {
        SubScene scene3d = new SubScene(group, VIEWPORT_SIZE, VIEWPORT_SIZE, true, SceneAntialiasing.BALANCED);
        scene3d.widthProperty().bind(((AnchorPane)meshGroup.getParent()).widthProperty());
        scene3d.heightProperty().bind(((AnchorPane)meshGroup.getParent()).heightProperty());

        scene3d.setFill(Color.GREY);
        scene3d.setCamera(this.camera);
        scene3d.setPickOnBounds(true);
        return scene3d;
    }

    private void showFigure() {
        meshView = buildMesh();
        // Add MeshView to Group
        Group meshInGroup = buildScene();
        // Create SubScene
        SubScene subScene = createScene3D(meshInGroup);
        // Add subScene to meshGroup
        this.meshGroup.getChildren().add(subScene);

        RotateTransition rotate = rotate3dGroup(meshInGroup);
        this.meshGroup.getChildren().add(createControls(rotate));
        meshInGroup.getChildren().add(buildAxes());
    }

    private HBox createControls(RotateTransition rotateTransition) {
        CheckBox cull = new CheckBox("Cull Back");
        for (MeshView face : meshView){
            face.cullFaceProperty().bind(
                    Bindings.when(
                            cull.selectedProperty())
                            .then(CullFace.BACK)
                            .otherwise(CullFace.NONE)
            );
        }
        CheckBox wireframe = new CheckBox("Wireframe");

        for (MeshView face : meshView) {
            face.drawModeProperty().bind(
                    Bindings.when(
                            wireframe.selectedProperty())
                            .then(DrawMode.LINE)
                            .otherwise(DrawMode.FILL)
            );
        }
        CheckBox rotate = new CheckBox("Rotate");
        rotate.selectedProperty().addListener(observable -> {
            if (rotate.isSelected()) {
                rotateTransition.play();
            } else {
                rotateTransition.pause();
            }
        });


        HBox controls = new HBox(10, rotate, cull, wireframe);
        controls.setPadding(new Insets(10));
        return controls;
    }


    private RotateTransition rotate3dGroup(Group group) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(10), group);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setFromAngle(0);
        rotate.setToAngle(360);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(RotateTransition.INDEFINITE);

        return rotate;
    }

    private MeshView[] buildMesh() {
        quadru3d quadru = new quadru3d();

        return quadru.getMeshView();
    }

    private Group buildAxes() {

        axis = new Group();

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(250, 1, 1);
        final Box yAxis = new Box(1, 250, 1);
        final Box zAxis = new Box(1, 1, 250);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axis.getChildren().addAll(xAxis,yAxis,zAxis);

        axis.setScaleX(MODEL_SCALE_FACTOR);
        axis.setScaleY(MODEL_SCALE_FACTOR);
        axis.setScaleZ(MODEL_SCALE_FACTOR);

        return axis;
    }
}