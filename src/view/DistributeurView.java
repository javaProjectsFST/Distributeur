package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DistributeurView extends ImageView{
    
    private final String bgPath="resources/distributeur.jpg";
    private final Image bgImg;
    
    public DistributeurView(){
        bgImg=new Image(bgPath);
        this.setImage(bgImg);
   }

}
