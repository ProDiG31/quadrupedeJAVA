package com.quadru;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

public class CameraCustom extends PerspectiveCamera {

    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 1450;


    public CameraCustom(Group root){
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(this);
        cameraXform3.setRotateZ(0);

        this.setNearClip(0.1);
        this.setFarClip(10000.0);
        this.setTranslateZ(-cameraDistance);
        cameraXform.ry.setAngle(0);
        cameraXform.rx.setAngle(0);
    }

    public Xform getCameraXform() { return this.cameraXform;}
    public Xform getCameraXform2(){ return this.cameraXform2;}
    public Xform getCameraXform3(){ return this.cameraXform3;}



}
