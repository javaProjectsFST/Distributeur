package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoissonView extends ImageView{
    private String imgPath;
    private Image img;
    private int type;
    
    public BoissonView(String imgPath, int type){
        this.imgPath=imgPath;
        img=new Image(this.imgPath);
        this.type=type;
        this.setImage(img);
    }
    
    public int getType(){
        return type;
    }
}
