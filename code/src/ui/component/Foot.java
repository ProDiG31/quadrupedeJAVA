package ui.component;

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
    }
}
