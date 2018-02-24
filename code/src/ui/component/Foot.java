package ui.component;

import javafx.animation.RotateTransition;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;

import static com.quadru.StlLoader.loadStlFile;

public class Foot extends Part {


    public Foot(double translateX, double translateY){
        PhongMaterial sample = new PhongMaterial(PartColor);
        sample.setSpecularColor(lightColor);
        sample.setSpecularPower(16);
        this.part3d = loadStlFile("leg.stl");
        Colorise(sample);
        Translate(translateX,0,translateY);
        Rotate(90F, Rotate.Z_AXIS);
        rotate3dGroup();

        Rotate order = new Rotate();
        System.out.println(order.angleProperty().toString());

    }

    private RotateTransition rotate3dGroup() {
        Rotate order = new Rotate();
        System.out.println(order.angleProperty().toString());

//        RotateTransition rotate = new RotateTransition(Duration.seconds(10), group);
//        rotate.setAxis(Rotate.X_AXIS);
//        rotate.setFromAngle(0);
//        rotate.setToAngle(180);
//        rotate.setInterpolator(Interpolator.LINEAR);
//        rotate.setCycleCount(RotateTransition.INDEFINITE);
//
//        return rotate;
        return null;
    }
}
