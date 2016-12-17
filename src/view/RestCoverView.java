package view;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class RestCoverView extends ImageView{
    private final String restCoverPath="resources/RestCover.png";
    private final Image restCoverImg;
    private final ImageView restView;
    
    private final TranslateTransition restCoverTT;
    
    public RestCoverView(){
        restCoverImg=new Image(restCoverPath);
        this.setImage(restCoverImg);
        
        restCoverTT=new TranslateTransition();
        restCoverTT.setDuration(Duration.millis(500));
        restCoverTT.setNode(this);
        
        restView=new ImageView(new Image("resources/pieces/rest.png"));
    }
    
    public ImageView getRestView(){
        return restView;
    }
    
    public void OpenRestCover(){
        restView.setVisible(true);
        restCoverTT.setToX(35);
        restCoverTT.play();
    }
    
    public void CloseRestCover(){
        restView.setVisible(false);
        restCoverTT.setToX(0);
        restCoverTT.play();
    }
}
