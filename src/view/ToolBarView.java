package view;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Money;

public class ToolBarView extends Pane{

    private ArrayList<Button> MoneyButtons;
    private final int ButtonSIZE=80;
    private final int ButtonOFFSET=10;
    
    public ToolBarView(ArrayList<Money> moneys){
        MoneyButtons=new ArrayList<Button>();
        for(Money m:moneys){
            Button b=new Button();
            if(MoneyButtons.size()==0){
                restyleButton(b, 0, m.getImgPath());
            }else{
                restyleButton(b, MoneyButtons.get(MoneyButtons.size()-1).getLayoutY(), m.getImgPath());
            }
            MoneyButtons.add(b);
        }
//        this.setBackground(new Background(new BackgroundFill(Color.web("#a2a2a2"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        this.getChildren().addAll(MoneyButtons);
        this.setMaxWidth(100);
        this.setMinWidth(100);
        this.setPrefWidth(100);
        
        this.setMaxHeight(1100);
        this.setMinHeight(1100);
        this.setPrefHeight(1100);
    }
    
    private void restyleButton(Button button, double buttonY, String imgPath){
        button.setGraphic(new ImageView(new Image(imgPath)));
        button.setFocusTraversable(false);
        button.setMinSize(ButtonSIZE, ButtonSIZE);
        button.setMaxSize(ButtonSIZE, ButtonSIZE);
        button.setPrefSize(ButtonSIZE, ButtonSIZE);
        
        button.setLayoutX(ButtonOFFSET);
        button.setLayoutY(buttonY+80+(2*ButtonOFFSET));
    }
    
    
    public void relocateMoneyButton(Button b){
        int index=MoneyButtons.indexOf(b);
        if(index==0){
            b.setLayoutX(ButtonOFFSET);
            b.setLayoutY(80+(2*ButtonOFFSET));
        }else{
            Button button=MoneyButtons.get(index-1);
        
            b.setLayoutX(ButtonOFFSET);
            b.setLayoutY(button.getLayoutY()+80+(2*ButtonOFFSET));
        }
        
    }
    
    public ArrayList<Button> getMoneyButton(){
        return MoneyButtons;
    }
}
