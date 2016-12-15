package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DistributeurView{
    
    private final String bgPath="resources/distributeurBG.png";
    private final Image bgImg;
    private final ImageView bgImgView;
    
    private final String fgPath="resources/distributeurFG.png";
    private final Image fgImg;
    private final ImageView fgImgView;
    
    public DistributeurView(){
        bgImg=new Image(bgPath);
        bgImgView=new ImageView(bgImg);
        
        fgImg=new Image(fgPath);
        fgImgView=new ImageView(fgImg);
    }

    public String getBgPath() {
        return bgPath;
    }

    public Image getBgImg() {
        return bgImg;
    }

    public ImageView getBgImgView() {
        return bgImgView;
    }

    public String getFgPath() {
        return fgPath;
    }

    public Image getFgImg() {
        return fgImg;
    }

    public ImageView getFgImgView() {
        return fgImgView;
    }
    
    
}
