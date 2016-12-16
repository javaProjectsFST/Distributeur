package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ToolBarView extends Pane{

    Button b;
    
    public ToolBarView(){
        b=new Button("hiiiiii");
        this.setBackground(new Background(new BackgroundFill(Color.web("#a2a2a2"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        this.getChildren().add(b);
        this.setMaxWidth(100);
        this.setMinWidth(100);
        this.setPrefWidth(100);
        
        this.setMaxHeight(1100);
        this.setMinHeight(1100);
        this.setPrefHeight(1100);
    }
    
    public void printParent(){
        System.out.println(this.getParent());
    }
    
}
