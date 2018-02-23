package ui.component;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;

import java.awt.font.GlyphJustificationInfo;
import java.util.ArrayList;

import static com.quadru.StlLoader.loadStlFile;

public class quadru3d extends Parent {

    private static final int VIEWPORT_SIZE = 800;
    private static final double MODEL_SCALE_FACTOR = 4;
    private static final double MODEL_X_OFFSET = 0; // standard
    private static final double MODEL_Y_OFFSET = 0; // standard
    private static final String PART_ROOT_PATH = "ui/component/part/";

    private static final double[][] footOffset ={{90,60},{90,-60},{-90,65},{-90,-65}};

    private static final Color lightColor = Color.rgb(244, 255, 250);
    private static final Color PartColor = Color.rgb(222, 12, 68);

    public ArrayList<MeshView[]> parts;

    public quadru3d(){
        parts = new ArrayList<>();
        buildFoot();
        buildBackBone();
        buildGhost();
    }

    public MeshView[] getMeshView() {
        int nbTot_face = 0;
        for (MeshView[] part : parts){
            nbTot_face += part.length;
        }

        MeshView[] quadru = new MeshView[nbTot_face];

        for (int i = 0; i < parts.size(); i++){
            for (MeshView face : parts.get(i)){
                quadru[i] = face;
            }
        }
        return quadru;
    }

    private void buildFoot(){
        for (double[] coord : footOffset){
            Foot feet = new Foot(coord[0], coord[1]);
            parts.add(feet.getPart3d());
        }
    }

    private void buildGhost(){

        Ghost ghost = new Ghost(0,0);
        parts.add(ghost.getPart3d());

    }

    private void buildBackBone(){

        PhongMaterial sample = new PhongMaterial(PartColor);
        sample.setSpecularColor(lightColor);
        sample.setSpecularPower(16);

        MeshView[] backBone = loadStlFile("backbone.stl");
        Colorise(backBone, sample);
        RotateZ(backBone,90F);
        parts.add(backBone);

    }

    private void buildLeg(){

        PhongMaterial sample = new PhongMaterial(PartColor);
        sample.setSpecularColor(lightColor);
        sample.setSpecularPower(16);

        for (double[] coord : footOffset){
            MeshView[] leg = loadStlFile("leg.stl");
            Colorise(leg, sample);
            Translate(leg, coord[0],0,coord[1]);
            RotateZ(leg,90F);
            parts.add(leg);
        }
    }

    /*
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

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        MeshView mesh = new MeshView();
        //mesh.setMesh(xAxis);
    }*/


    private void RotateZ(MeshView[] part, double rotationAngle) {
        for (MeshView face : part) {
            face.setRotate(rotationAngle);
        }
    }

    private void Colorise(MeshView[] part, PhongMaterial sample){
        for (MeshView face : part) {
            face.setMaterial(sample);
        }
    }

    private void Translate(MeshView[] part, double xTranslate, double yTranslate, double zTranslate){
        for (MeshView face : part) {
            face.setTranslateX(xTranslate * MODEL_SCALE_FACTOR);
            face.setTranslateY(yTranslate * MODEL_SCALE_FACTOR);
            face.setTranslateZ(zTranslate * MODEL_SCALE_FACTOR);
        }
    }

}
