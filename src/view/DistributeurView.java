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

    private final int[][] PositionsBtn;
    
    public DistributeurView(){
        
         PositionsBtn=new int[][]{
            {849, 410}, 
            {849, 437}, 
            {849, 465},
            {849, 493}, 
            {849, 520}, 
            {849, 547},
            {849, 575}, 
            {849, 602}, 
            {849, 630},
            {849, 658}, 
            {849, 685},
            {849, 713},
        };
         
        bgImg=new Image(bgPath);
        bgImgView=new ImageView(bgImg);
        
        fgImg=new Image(fgPath);
        fgImgView=new ImageView(fgImg);
        
        sand1Btn=new Button("Sandwich Boeuf");
        sand1Btn.relocate(848, 410);
        sand1Btn.setMinHeight(24);
        sand1Btn.setMaxHeight(24);
        sand1Btn.setPrefHeight(24);
        sand1Btn.setMaxWidth(84);
        sand1Btn.setMinWidth(84);
        sand1Btn.setPrefWidth(84);
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
