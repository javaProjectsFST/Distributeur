package view;

import java.awt.event.MouseEvent;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MoneyCoverView extends ImageView{
    private final String moneyCoverPath="resources/MoneyCover.png";
    private final Image moneyCoverImg;
    
    private final TranslateTransition moneyCoverTT;
    
    public MoneyCoverView(){
        moneyCoverImg=new Image(moneyCoverPath);
        this.setImage(moneyCoverImg);
        
        moneyCoverTT=new TranslateTransition();
        moneyCoverTT.setDuration(Duration.millis(500));
        moneyCoverTT.setNode(this);
    }
    
    public void OpenMoneyCover(){
        moneyCoverTT.setToX(80);
        moneyCoverTT.play();
    }
    
    public void CloseMoneyCover(){
        moneyCoverTT.setToX(0);
        moneyCoverTT.play();
    }
}
