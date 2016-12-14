package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LeftCoverView extends ImageView{
    private final String leftCoverPath="resources/LeftCover.png";
    private final Image leftCoverImg;
    
    public LeftCoverView(){
        leftCoverImg=new Image(leftCoverPath);
        this.setImage(leftCoverImg);
    }
}
