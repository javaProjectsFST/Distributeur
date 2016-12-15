package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DistributeurView{
    
    private final String bgPath="resources/distributeurBG.png";
    private final Image bgImg;
    private final ImageView bgImgView;
    
    private final String fgPath="resources/distributeurFG.png";
    private final Image fgImg;
    private final ImageView fgImgView;
    
    private final Button sand1Btn;
    
    public DistributeurView(){
        bgImg=new Image(bgPath);
        bgImgView=new ImageView(bgImg);
        
        fgImg=new Image(fgPath);
        fgImgView=new ImageView(fgImg);
        
        sand1Btn=new Button("Sandwich Boeuf");
        sand1Btn.relocate(849, 410);
        sand1Btn.setMinHeight(24);
        sand1Btn.setMaxHeight(24);
        sand1Btn.setPrefHeight(24);
        sand1Btn.setMaxWidth(82);
        sand1Btn.setMinWidth(82);
        sand1Btn.setPrefWidth(82);
        sand1Btn.setStyle("-fx-font: 8 arial");
        
    }
    
    public Button getbtn(){
        return sand1Btn;
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
