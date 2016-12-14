package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RightCoverView extends ImageView{
    private final String rightCoverPath="resources/RightCover.png";
    private final Image rightCoverImg;
    
    public RightCoverView(){
        rightCoverImg=new Image(rightCoverPath);
        this.setImage(rightCoverImg);
    }
}
