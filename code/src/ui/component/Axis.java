package ui.component;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Axis extends Group {

    private Box xAxis;
    private Box yAxis;
    private Box zAxis;

    public Axis(double MODEL_SCALE_FACTOR){

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        xAxis = new Box(250, 1, 1);
        yAxis = new Box(1, 250, 1);
        zAxis = new Box(1, 1, 250);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        this.getChildren().addAll(xAxis,yAxis,zAxis);
        this.setScaleX(MODEL_SCALE_FACTOR);
        this.setScaleY(MODEL_SCALE_FACTOR);
        this.setScaleZ(MODEL_SCALE_FACTOR);
    }


    public Box getzAxis() {
        return zAxis;
    }

    public void setzAxis(Box zAxis) {
        this.zAxis = zAxis;
    }


    public Box getxAxis() {
        return xAxis;
    }

    public void setxAxis(Box xAxis) {
        this.xAxis = xAxis;
    }

    public Box getyAxis() {
        return yAxis;
    }

    public void setyAxis(Box yAxis) {
        this.yAxis = yAxis;
    }


}
