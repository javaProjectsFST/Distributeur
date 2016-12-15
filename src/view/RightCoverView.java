package view;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class RightCoverView extends ImageView{
    private final String rightCoverPath="resources/RightCover.png";
    private final Image rightCoverImg;
    
    private final TranslateTransition rightCoverTT;
    
    public RightCoverView(){
        rightCoverImg=new Image(rightCoverPath);
        this.setImage(rightCoverImg);
        
        rightCoverTT=new TranslateTransition();
        rightCoverTT.setDuration(Duration.millis(500));
        rightCoverTT.setNode(this);
    }
    
    boolean rightCoverIsOpen=false;
    public void OpenCloseRightCover(){
        if(rightCoverIsOpen){
            rightCoverTT.setToY(0);
            rightCoverTT.play();
            rightCoverIsOpen=false;
        }else{
            rightCoverTT.setToY(-45);
            rightCoverTT.play();
            rightCoverIsOpen=true;
        }
    }
}
