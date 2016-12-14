package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LeftGlareView extends ImageView{
    private final String leftGlarePath="resources/LeftGlare.png";
    private final Image leftGlareImg;
    
    public LeftGlareView(){
        leftGlareImg=new Image(leftGlarePath);
        this.setImage(leftGlareImg);
    }
}
