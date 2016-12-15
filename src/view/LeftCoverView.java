package view;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LeftCoverView extends ImageView{
    private final String leftCoverPath="resources/LeftCover.png";
    private final Image leftCoverImg;
    
    private final TranslateTransition leftCoverTT;
    
    public LeftCoverView(){
        leftCoverImg=new Image(leftCoverPath);
        this.setImage(leftCoverImg);
        
        leftCoverTT=new TranslateTransition();
        leftCoverTT.setDuration(Duration.millis(500));
        leftCoverTT.setNode(this);
    }
    
    public void OpenLeftCover(){
        leftCoverTT.setToY(-45);
        leftCoverTT.play();
    }
    
    public void CloseLeftCover(){
        leftCoverTT.setToY(0);
        leftCoverTT.play();
    }
}
