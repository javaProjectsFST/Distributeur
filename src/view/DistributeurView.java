package view;

import java.util.ArrayList;
import java.util.List;
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
    
    private final ArrayList<Button> distributeurButtons;

    private final int[][] buttonsPositions;
    private final String[] buttonsNames;
    private final int BTNWidth=84;
    private final int BTNHeight=24;
    private final String BTNStyle="-fx-font: 8 arial";
    
    private double viewX;
    
    public DistributeurView(){
        
        buttonsPositions=new int[][]{
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
         
        buttonsNames=new String[]{
            "Sandwich Boeuf",
            "Panini Omelette",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        };
         
        bgImg=new Image(bgPath);
        bgImgView=new ImageView(bgImg);
        
        fgImg=new Image(fgPath);
        fgImgView=new ImageView(fgImg);
        
        distributeurButtons=new ArrayList<Button>();
        
        for(int i=0; i<12; i++){
            Button b=new Button(buttonsNames[i]);
            restyleBotton(b);
            b.relocate(buttonsPositions[i][0], buttonsPositions[i][1]);
            distributeurButtons.add(b);
        }
    }
    
    public double getViewX(){
        return viewX;
    }
    
    public void setViewX(double viewX){
        this.viewX=viewX;
        bgImgView.setLayoutX(viewX);
        fgImgView.setLayoutX(viewX);
        for(int i=0; i<12; i++){
            distributeurButtons.get(i).relocate(buttonsPositions[i][0]+viewX, buttonsPositions[i][1]);
        }
    }
    
    private void restyleBotton(Button btn){
        btn.setMinHeight(BTNHeight);
        btn.setMaxHeight(BTNHeight);
        btn.setPrefHeight(BTNHeight);
        btn.setMaxWidth(BTNWidth);
        btn.setMinWidth(BTNWidth);
        btn.setPrefWidth(BTNWidth);
        btn.setStyle(BTNStyle);
    }
    
    public int[][] getButtonsPositions(){
        return buttonsPositions;
    }
    
    public ArrayList<Button> getButtons(){
        return distributeurButtons;
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
