package ui.component;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;

import static com.quadru.StlLoader.loadStlFile;

public class Ghost extends Part {

    public Ghost(double translateX, double translateZ){

        PhongMaterial sample = new PhongMaterial(Color.rgb(82, 75, 222,.4F));
        sample.setSpecularColor(lightColor);
        sample.setSpecularPower(16);
        this.part3d = loadStlFile("assembly.stl");
        Colorise(sample);
        Translate(translateX,0,translateZ);
        Rotate(90F,Rotate.X_AXIS);
    }
}
