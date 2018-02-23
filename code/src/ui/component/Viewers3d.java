package ui.component;

import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

import java.io.File;

public class Viewers3d {

//    Scene view_Renderer ;

    private BorderPane root;
    private Group world;
    private MeshView[] part3d;
    private double sceneX, sceneY=0;
    private double fixedXAngle, fixedYAngle=0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private PerspectiveCamera camera;

    private static final int VIEWPORT_SIZE = 800;

    private static final double MODEL_SCALE_FACTOR = 4;
    private static final double MODEL_X_OFFSET = 0; // standard
    private static final double MODEL_Y_OFFSET = 0; // standard

    private static final Color lightColor = Color.rgb(244, 255, 250);
    private static final Color PartColor = Color.rgb(222, 12, 68);
    private static final Color PartColor2 = Color.rgb(79, 92, 222);

    final double cameraDistance = 1450;
    private final double cameraModifier = 20.0;
    private final double cameraQuantity = 0.50;

    public static boolean ctrlPressedDown = false;

    private PointLight pointLight;
    private SubScene subscene;
    final Group axisGroup = new Group();

    private double MaxZ ;
    private quadru3d quadru;

    public Viewers3d(){

        world = buildScene();
        System.out.println("build mesh ok ");

  //      root = new BorderPane();
 //       root.setCenter(world);
//        BorderPane.setMargin(world, new Insets(10F));

        subscene = new SubScene(world,750, 800, true, SceneAntialiasing.BALANCED);
        System.out.println("build subscene ok ");

        this.camera = addCamera(subscene);
        System.out.println("add camera Ok");

        subscene.setFill(Color.web("#293133"));

        hookupRotate(subscene);
        buildAxes();
        buildPlan();
       // buildUI();

//        final String cssDefault = "-fx-border-color: Black;\n"
  //              + "-fx-border-radius: 30;\n"
    //            + "-fx-border-width: 4;\n"
      //          + "-fx-padding: 20;\n";

    }
/*
    private MeshView[] loadStlFile(String pathStlFile){
        StlMeshImporter stlImporter = new StlMeshImporter();

        File stlFile = new File(pathStlFile);
        System.out.println("PATH = " + stlFile.getPath());
        System.out.println("EXIST = " + stlFile.exists());
        //System.out.println("File = " + stlFile.list().toString());

        if (stlFile.exists()) stlImporter.read(stlFile);
        Mesh mesh = stlImporter.getImport();
        stlImporter.close();
        return new MeshView[]{ new MeshView(mesh) };
    }

    private MeshView[] loadStl(String stlFilePath){
        MeshView[] obj3d = loadStlFile(stlFilePath);
        for (int i = 0; i < obj3d.length; i++) {

            obj3d[i].setTranslateX(VIEWPORT_SIZE / 2 + MODEL_X_OFFSET);
            obj3d[i].setTranslateY(VIEWPORT_SIZE / 2 + MODEL_Y_OFFSET);
            obj3d[i].setTranslateZ(VIEWPORT_SIZE / 2);
            obj3d[i].setScaleX(MODEL_SCALE_FACTOR);
            obj3d[i].setScaleY(MODEL_SCALE_FACTOR);
            obj3d[i].setScaleZ(MODEL_SCALE_FACTOR);
            PhongMaterial sample = new PhongMaterial(PartColor);
            sample.setSpecularColor(lightColor);
            sample.setSpecularPower(16);
            obj3d[i].setMaterial(sample);

        }
        return obj3d;
    }
  */

    private Group buildScene(){

        quadru = new quadru3d();

       // part3d = quadru.leg1_1;

        pointLight = new PointLight(lightColor);
        pointLight.setTranslateX(VIEWPORT_SIZE*3/4);
        pointLight.setTranslateY(VIEWPORT_SIZE/2);
        pointLight.setTranslateZ(VIEWPORT_SIZE/2);
        PointLight pointLight2 = new PointLight(lightColor);
        pointLight2.setTranslateX(VIEWPORT_SIZE*1/4);
        pointLight2.setTranslateY(VIEWPORT_SIZE*3/4);
        pointLight2.setTranslateZ(VIEWPORT_SIZE*3/4);
        PointLight pointLight3 = new PointLight(lightColor);
        pointLight3.setTranslateX(VIEWPORT_SIZE*5/8);
        pointLight3.setTranslateY(VIEWPORT_SIZE/2);
        pointLight3.setTranslateZ(0);

        Color ambientColor = Color.rgb(80, 80, 80, 0);
        AmbientLight ambient = new AmbientLight(ambientColor);

        Group world = new Group(part3d);
        
        world.getChildren().add(pointLight);
        world.getChildren().add(pointLight2);
        world.getChildren().add(pointLight3);
        world.getChildren().add(ambient);

        MaxZ = Math.max(part3d[0].getBoundsInLocal().getWidth(),
                Math.max(part3d[0].getBoundsInLocal().getHeight(),
                        part3d[0].getBoundsInLocal().getDepth()));

        return world;
    }

    private void buildPlan(){
        Box plan = new Box(3000,1,3000);

        plan.setScaleX(MODEL_SCALE_FACTOR);
        plan.setScaleZ(MODEL_SCALE_FACTOR);
        plan.setScaleY(MODEL_SCALE_FACTOR);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.rgb(0,0,0,0.5));
        whiteMaterial.setSpecularColor(Color.rgb(0,0,0,0.5));
        plan.setMaterial(whiteMaterial);

        world.getChildren().add(plan);
    }

    private void buildUI(){

        JFXTextField valueX = new JFXTextField("HELLO");
        valueX.setLayoutX(50);
        valueX.setLayoutY(50);
        //valueX.setTranslateZ(2);
        root.setRight(valueX);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(3000, 1, 1);
        final Box yAxis = new Box(1, 3000, 1);
        final Box zAxis = new Box(1, 1, 3000);
/*
        for (int i = 0; i < xAxis.getWidth(); i = (i + (int)MODEL_SCALE_FACTOR)){
            Box grad = new Box(1,5,5);
            grad.setTranslateX(i);
            grad.setMaterial(redMaterial);
            axisGroup.getChildren().add(grad);
        }

        for (int i = 0; i < yAxis.getHeight(); i = (i + (int)MODEL_SCALE_FACTOR)){
            Box grad = new Box(5,1,5);
            grad.setTranslateY(i);
            grad.setMaterial(blueMaterial);
            axisGroup.getChildren().add(grad);
        }

        for (int i = 0; i < zAxis.getDepth(); i = (i + (int)MODEL_SCALE_FACTOR)){
            Box grad = new Box(5,5,1);
            grad.setTranslateZ(i);
            grad.setMaterial(greenMaterial);
            axisGroup.getChildren().add(grad);
        }*/
        //xAxis.getBoundsInLocal().getWidth()

        //yAxis.setTranslateY(-150);
        //yAxis.setTranslateX(150);

        //zAxis.setTranslateY(-150);
        //zAxis.setTranslateZ(150);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);

        //axisGroup.setTranslateX(500);
        //axisGroup.setTranslateZ(-500);
        //axisGroup.setTranslateY(-500);

        axisGroup.setScaleX(MODEL_SCALE_FACTOR);
        axisGroup.setScaleY(MODEL_SCALE_FACTOR);
        axisGroup.setScaleZ(MODEL_SCALE_FACTOR);

        world.getChildren().addAll(axisGroup);
    }

    private PerspectiveCamera addCamera(SubScene scene_to_insert_cam) {

        PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
        perspectiveCamera.setFarClip(10000.0F);

        perspectiveCamera.setTranslateZ(-this.cameraDistance);
        perspectiveCamera.setTranslateX(-this.cameraDistance);
        perspectiveCamera.setTranslateY(-this.cameraDistance);

        System.out.println("Near Clip: " + perspectiveCamera.getNearClip());
        System.out.println("Far Clip:  " + perspectiveCamera.getFarClip());
        System.out.println("FOV:       " + perspectiveCamera.getFieldOfView());

        perspectiveCamera.setFieldOfView(100);

        scene_to_insert_cam.setCamera(perspectiveCamera);
        return perspectiveCamera;
    }

    public SubScene getSubcene(){
        return this.subscene;
    }

    public void handleKeyboard(Scene scene){
        scene.setOnKeyPressed(event -> {
            double change = cameraQuantity * MODEL_SCALE_FACTOR ;

            Rotate xRotate = new Rotate(5, 0,0,0,Rotate.X_AXIS);
            Rotate yRotate = new Rotate(5, 0,0,0,Rotate.Y_AXIS);
            Rotate zRotate = new Rotate(5, 0,0,0,Rotate.Z_AXIS);

            //Add shift modifier to simulate "Running Speed"
            if(event.isShiftDown()) { change = cameraModifier * MODEL_SCALE_FACTOR; }
            //What key did the user press?
            KeyCode keycode = event.getCode();
            System.out.println("key handle "+ event.getCode());

            // Add Zoom controls
            if(keycode == KeyCode.UP) {
                camera.setTranslateZ(camera.getTranslateZ() + change);
            }
            if(keycode == KeyCode.DOWN) {
                camera.setTranslateZ(camera.getTranslateZ() - change);
            }

            // Add Strafe controls
            if(keycode == KeyCode.B) {
                camera.setTranslateY(camera.getTranslateY() - change);
            }
            if(keycode == KeyCode.N) {
                camera.setTranslateY(camera.getTranslateY() + change);
            }

            if(keycode == KeyCode.LEFT) {
                camera.setTranslateX(camera.getTranslateX() - change);
            }
            if(keycode == KeyCode.RIGHT) {
                camera.setTranslateX(camera.getTranslateX() + change);
            }

            if (keycode == KeyCode.Q) {
                camera.setRotationAxis(Rotate.Y_AXIS);
                camera.setRotate( camera.getRotate() + 10.0);
            }
            if (keycode == KeyCode.E) {
                camera.setRotationAxis(Rotate.Y_AXIS);
                camera.setRotate( camera.getRotate() - 10.0);
            }
            if (keycode == KeyCode.R) {
                camera.setRotationAxis(Rotate.Y_AXIS);
                camera.setRotate(0);
                camera.setRotationAxis(Rotate.X_AXIS);
                camera.setRotate(0);
                camera.setRotationAxis(Rotate.Z_AXIS);
                camera.setRotate(0);
                camera.setTranslateX(0);
                camera.setTranslateY(0);
                camera.setTranslateZ(0);

            }

            if(keycode == KeyCode.O) {
                camera.setFieldOfView(camera.getFieldOfView() + 5F);
                System.out.println("FOV  = " + camera.getFieldOfView());
            }


            if(keycode == KeyCode.P) {
                camera.setFieldOfView(camera.getFieldOfView() - 5F);
                System.out.println("FOV  = " + camera.getFieldOfView());
            }

            if (keycode == KeyCode.CONTROL) {
                ctrlPressedDown = true;
            }

            if(keycode == KeyCode.T){
                camera.setRotationAxis(Rotate.X_AXIS);
                camera.setRotate(camera.getRotate() + 5);
            }
            if(keycode == KeyCode.E){
                camera.setRotationAxis(Rotate.Z_AXIS);
                camera.setRotate(camera.getRotate() + 5);
            }
            if(keycode == KeyCode.A){
                camera.setRotationAxis(Rotate.Z_AXIS);
                camera.setRotate(camera.getRotate() + 5);
            }

            /*
            if (keycode == KeyCode.NUMPAD2) {
                camera.getTransforms().add(yRotate);
            }
            if (keycode == KeyCode.NUMPAD1) {
                camera.getTransforms().add((xRotate));
            }
            if (keycode == KeyCode.NUMPAD3) {
                camera.getTransforms().add(zRotate);
            }
            */

        });

        scene.setOnKeyReleased(releaseEvent -> {

            KeyCode keycode = releaseEvent.getCode();

            if (keycode == KeyCode.CONTROL) {
                ctrlPressedDown = false;
            }

        });
    }

    private void hookupRotate(SubScene subscene){

        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        world.getTransforms().addAll(xRotate, yRotate);
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        subscene.setOnMousePressed(event -> {
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();
            fixedXAngle = angleX.get();
            fixedYAngle = angleY.get();
        });

        subscene.setOnMouseDragged(event ->{
            angleX.set(fixedXAngle - (sceneX - event.getSceneY()));
            angleY.set(fixedYAngle + (sceneY - event.getSceneX()));
        });

        subscene.setOnMouseEntered(event ->{
            PhongMaterial sample = new PhongMaterial(PartColor2);
            sample.setSpecularColor(lightColor);
            sample.setSpecularPower(16);
            for (MeshView part : part3d){
                part.setMaterial(sample);
            }
        });

        subscene.setOnMouseExited(event ->{
            PhongMaterial sample = new PhongMaterial(PartColor);
            sample.setSpecularColor(lightColor);
            sample.setSpecularPower(16);
            for (MeshView part : part3d){
                part.setMaterial(sample);
            }
        });

    }

}
