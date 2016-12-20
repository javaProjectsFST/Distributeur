package view;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DistributeurView{
    
    private final String bgPath="resources/distributeurBG.png";
    private final Image bgImg;
    private final ImageView bgImgView;
    
    private final String fgPath="resources/distributeurFG.png";
    private final Image fgImg;
    private final ImageView fgImgView;
    
    private Label sumLabel;
    
    private ImageView ScreenMessageOne;
    private ImageView screenErrorMessage;
    private ImageView screenSuccessMessage;
    private ImageView screenWaitMessage;
    
    private final ArrayList<Button> distributeurButtons;

    private final int[][] buttonsPositions;
    private final String[] buttonsNames;
    private final int BTNWidth=84;
    private final int BTNHeight=24;
    private final String BTNStyle="-fx-font: 8 arial";
    
    private ArrayList<Button> screenButtons;
    
    private double viewX;
    
    public DistributeurView(){
        
        buttonsPositions=new int[][]{
           {949, 274}, 
           {949, 301}, 
           {949, 329},
           {949, 357}, 
           {949, 384}, 
           {949, 411},
           {949, 439}, 
           {949, 466}, 
           {949, 494},
           {949, 522}, 
           {949, 549},
           {949, 577},
        };
        
        ScreenMessageOne=new ImageView(new Image("resources/screenMessageOne.png"));
        ScreenMessageOne.setLayoutX(100);
        
        screenErrorMessage=new ImageView(new Image("resources/screenErrorMessage.png"));
        screenErrorMessage.setLayoutX(100);
        
        screenSuccessMessage=new ImageView(new Image("resources/screenMessageSucces.png"));
        screenSuccessMessage.setLayoutX(100);
        
        screenWaitMessage=new ImageView(new Image("resources/screenWaitMessage.png"));
        screenWaitMessage.setLayoutX(100);
        
        screenButtons=new ArrayList<Button>();
        Button bt=new Button();
        bt.setStyle("-fx-font: 9 arial; -fx-base: #42cd8d;");
        bt.setText("1");
        bt.setFocusTraversable(false);
        bt.setLayoutX(961);
        bt.setLayoutY(120);
        bt.setMinSize(19, 19);
        bt.setMaxSize(19, 19);
        bt.setPrefSize(19, 19);
        
        screenButtons.add(bt);
        for(int i=1; i<9; i++){
            Button b=new Button(String.valueOf(i+1));
            b.setFocusTraversable(false);
            b.setStyle("-fx-font: 9 arial; -fx-base: #42cd8d;");
            b.setMinSize(screenButtons.get(i-1).getPrefWidth(), screenButtons.get(i-1).getPrefHeight());
            b.setMaxSize(screenButtons.get(i-1).getPrefWidth(), screenButtons.get(i-1).getPrefHeight());
            b.setPrefSize(screenButtons.get(i-1).getPrefWidth(), screenButtons.get(i-1).getPrefHeight());
            if(i==3 || i==6){
                b.setLayoutY(screenButtons.get(i-1).getLayoutY()+screenButtons.get(i-1).getPrefHeight());
                b.setLayoutX(961);
            }else{
                b.setLayoutY(screenButtons.get(i-1).getLayoutY());
                b.setLayoutX(screenButtons.get(i-1).getLayoutX()+screenButtons.get(i-1).getPrefWidth());
            }
            screenButtons.add(b);
        }
        
        bt=new Button("0");
        bt.setFocusTraversable(false);
        bt.setStyle("-fx-font: 9 arial; -fx-base: #42cd8d;");
        bt.setLayoutX(980);
        bt.setLayoutY(screenButtons.get(screenButtons.size()-1).getLayoutY()+19);
        bt.setMinSize(19, 19);
        bt.setMaxSize(19, 19);
        bt.setPrefSize(19, 19);
        
        screenButtons.add(bt);
        buttonsNames=new String[]{
            "Sandwich Boeuf",
            "Panini Omelette",
            "Panini Boeuf",
            "Panini Salami",
            "Sandwich Omelette",
            "Sandwich Salade",
            "Coke",
            "Water",
            "Fanta",
            "Cidre",
            "Petillante",
            "Boga"
        };
         
        bgImg=new Image(bgPath);
        bgImgView=new ImageView(bgImg);
        
        fgImg=new Image(fgPath);
        fgImgView=new ImageView(fgImg);
        
        distributeurButtons=new ArrayList<Button>();
        
        sumLabel=new Label("0000");
        sumLabel.setFont(Font.font("DS-Digital", 20));
        sumLabel.setTextFill(Color.RED);
        
        sumLabel.relocate(975, 202);
        
        for(int i=0; i<12; i++){
            Button b=new Button(buttonsNames[i]);
            restyleBotton(b);
            b.relocate(buttonsPositions[i][0], buttonsPositions[i][1]);
            distributeurButtons.add(b);
        }
    }
    
    public ImageView getScreenWaitMessage(){
        return screenWaitMessage;
    }
    
    public ImageView getScreenErrorMessage(){
        return screenErrorMessage;
    }
    
    public ImageView getScreenSuccesMessage(){
        return screenSuccessMessage;
    }
    
    public ImageView getScrennMessageOne(){
        return ScreenMessageOne;
    }
    
    public ArrayList<Button> getScreenButtons(){
        return screenButtons;
    }
    
    public void setLabelText(String s){
        while(s.length()<4){
            s="0"+s;
        }
        sumLabel.setText(s);
    }
    
    public Label getSumLabel(){
        return sumLabel;
    }
    
    public double getViewX(){
        return viewX;
    }
    
    public void setViewX(double viewX){
        this.viewX=viewX;
        bgImgView.setLayoutX(viewX);
        fgImgView.setLayoutX(viewX);
    }
    
    private void restyleBotton(Button btn){
        btn.setFocusTraversable(false);
        btn.setMinHeight(BTNHeight);
        btn.setMaxHeight(BTNHeight);
        btn.setPrefHeight(BTNHeight);
        btn.setMaxWidth(BTNWidth);
        btn.setMinWidth(BTNWidth);
        btn.setPrefWidth(BTNWidth);
        btn.setStyle(BTNStyle);
    }
    
    public void setRest(int Rest){
        
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
