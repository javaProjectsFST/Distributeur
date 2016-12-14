package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SandwichView extends ImageView{
    private String imgPath;
    private Image img;
    
    public SandwichView(String imgPath){
        this.imgPath=imgPath;
        img=new Image(this.imgPath);
        
        this.setImage(img);
    }
}
