package ui.component;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

public abstract class Part {

    protected static final int MODEL_SCALE_FACTOR = 4;
    protected static final Color lightColor = Color.rgb(244, 255, 250);
    protected static final Color PartColor = Color.rgb(222, 12, 68);

    /*
    Rotate rx = new Rotate();
    { rx.setAxis(Rotate.X_AXIS); }
    Rotate ry = new Rotate();
    { ry.setAxis(Rotate.Y_AXIS); }
    Rotate rz = new Rotate();
    { rz.setAxis(Rotate.Z_AXIS); }
    */

    protected MeshView[] part3d;

    public MeshView[] getPart3d() {
        return part3d;
    }

    protected void Rotate(double rotationAngle, Point3D axis) {
        for (MeshView face : part3d) {
            face.setRotationAxis(axis);
            face.setRotate(face.getRotate() + rotationAngle);
        }
    }


    protected void Colorise(PhongMaterial sample){
        for (MeshView face : part3d) {
            face.setMaterial(sample);
        }
    }

    protected void Translate(double xTranslate, double yTranslate, double zTranslate){
        for (MeshView face : part3d) {
            face.setTranslateX(xTranslate * MODEL_SCALE_FACTOR);
            face.setTranslateY(yTranslate * MODEL_SCALE_FACTOR);
            face.setTranslateZ(zTranslate * MODEL_SCALE_FACTOR);
        }
    }

}
