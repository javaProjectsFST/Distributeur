package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScreenGlareView extends ImageView{
    private final String screenGlarePath="resources/ScreenGlare.png";
    private final Image screenGlareImg;
    
    public ScreenGlareView(){
        screenGlareImg=new Image(screenGlarePath);
        this.setImage(screenGlareImg);
    }
}
