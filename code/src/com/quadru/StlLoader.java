package com.quadru;

import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;

import java.io.File;

public class StlLoader {


    private static final int VIEWPORT_SIZE = 800;

    private static final double MODEL_SCALE_FACTOR = 4;
    private static final double MODEL_X_OFFSET = 0; // standard
    private static final double MODEL_Y_OFFSET = 0; // standard
    private static final String PART_ROOT_PATH = "C:\\Workspace\\Arduino\\QuadrupedeJava\\quadrupedeJAVA\\code\\src\\ui\\component\\Part\\";

    public static MeshView[] loadStlFile(String pathStlFile){
        StlMeshImporter stlImporter = new StlMeshImporter();

        File stlFile = new File(PART_ROOT_PATH + pathStlFile);
        System.out.println("PATH = " + stlFile.getPath());
        System.out.println("EXIST = " + stlFile.exists());

        if (stlFile.exists()) stlImporter.read(stlFile);
        Mesh mesh = stlImporter.getImport();
        stlImporter.close();
        MeshView[] obj3d = new MeshView[]{ new MeshView(mesh) };

        for (int i = 0; i < obj3d.length; i++) {

            obj3d[i].setTranslateX(VIEWPORT_SIZE / 2 + MODEL_X_OFFSET);
            obj3d[i].setTranslateY(VIEWPORT_SIZE / 2 + MODEL_Y_OFFSET);
            obj3d[i].setTranslateZ(VIEWPORT_SIZE / 2);
            obj3d[i].setScaleX(MODEL_SCALE_FACTOR);
            obj3d[i].setScaleY(MODEL_SCALE_FACTOR);
            obj3d[i].setScaleZ(MODEL_SCALE_FACTOR);

        }

        return obj3d;

    }

}
