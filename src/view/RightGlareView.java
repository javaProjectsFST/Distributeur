package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RightGlareView extends ImageView{
    private final String rightGlarePath="resources/RightGlare.png";
    private final Image rightGlareImg;
    
    public RightGlareView(){
        rightGlareImg=new Image(rightGlarePath);
        this.setImage(rightGlareImg);
    }
}
