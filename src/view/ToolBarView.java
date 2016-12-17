package view;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Money;

public class ToolBarView extends Pane{

    private ArrayList<Button> MoneyButtons;
    private Button creditCardButton;
    private final int ButtonSIZE=80;
    private final int ButtonOFFSET=10;
    private Point moneyPoint;
    private Point cardPoint;
    private Button login;
    
    public ToolBarView(ArrayList<Money> moneys){
        MoneyButtons=new ArrayList<Button>();
        for(Money m:moneys){
            Button b=new Button();
            if(MoneyButtons.size()==0){
                restyleButton(b, null, m.getImgPath());
            }else{
                restyleButton(b, MoneyButtons.get(MoneyButtons.size()-1), m.getImgPath());
            }
            MoneyButtons.add(b);
        }
        ImageView cardView=new ImageView(new Image("resources/pieces/CreditCard.png"));
        creditCardButton=new Button();
        creditCardButton.setMinSize(cardView.getImage().getWidth(), cardView.getImage().getHeight());
        creditCardButton.setMaxSize(cardView.getImage().getWidth(), cardView.getImage().getHeight());
        creditCardButton.setPrefSize(cardView.getImage().getWidth(), cardView.getImage().getHeight());
        creditCardButton.setGraphic(cardView);
        creditCardButton.setLayoutX(ButtonOFFSET);
        creditCardButton.setLayoutY(MoneyButtons.get(MoneyButtons.size()-1).getLayoutY()+MoneyButtons.get(MoneyButtons.size()-1).getPrefHeight()+20);
        
//        this.setBackground(new Background(new BackgroundFill(Color.web("#a2a2a2"), CornerRadii.EMPTY, Insets.EMPTY)));
        moneyPoint=new Point();
        moneyPoint.setX(920);
        moneyPoint.setY(195);
        
        cardPoint=new Point();
        cardPoint.setX(920);
        cardPoint.setY(50);
        
        login=new Button("Login");
        login.setMinSize(80, 80);
        login.setMaxSize(80, 80);
        login.setPrefSize(80, 80);
        login.setStyle("-fx-font: 20 arial");
        login.setLayoutX(ButtonOFFSET);
        login.setLayoutY(997-150);
        
        this.getChildren().add(login);
        this.getChildren().addAll(MoneyButtons);
        this.getChildren().add(creditCardButton);
        this.setMaxWidth(100);
        this.setMinWidth(100);
        this.setPrefWidth(100);
        
        this.setMaxHeight(1100);
        this.setMinHeight(1100);
        this.setPrefHeight(1100);
    }
    
    public Button getLoginButton(){
        return login;
    }
    
    public Point getCardPoint(){
        return cardPoint;
    }
    
    public Button getCreditCardButton(){
        return creditCardButton;
    }
    
    public Point getMoneyPoint(){
        return moneyPoint;
    }
    
    private void restyleButton(Button button, Button b, String imgPath){
        Image img=new Image(imgPath);
        button.setGraphic(new ImageView(img));
        button.setFocusTraversable(false);
        button.setMinSize(img.getWidth(), img.getWidth());
        button.setMaxSize(img.getWidth(), img.getWidth());
        button.setPrefSize(img.getWidth(), img.getWidth());
        button.setStyle("-fx-background-radius: 50%;");
        
        double previousImgWidth;
        double previousButtonY;
        if(b==null){
            previousImgWidth=0;
            previousButtonY=0;
        }else{
            previousImgWidth=((ImageView)b.getGraphic()).getImage().getWidth();
            previousButtonY=b.getLayoutY();
        }
        button.setLayoutX(50-(img.getWidth()/2));
        button.setLayoutY(previousButtonY+previousImgWidth+(2*ButtonOFFSET));
    }
    
    
    public void relocateMoneyButton(Button b){
        int index=MoneyButtons.indexOf(b);
        double imgWidth=((ImageView)b.getGraphic()).getImage().getWidth();
        
        b.setLayoutX(50-(imgWidth/2));
        if(index==0){
            b.setLayoutY(2*ButtonOFFSET);
        }else{
            Button button=MoneyButtons.get(index-1);
            double previousImgWidth=((ImageView)button.getGraphic()).getImage().getWidth();
        
            b.setLayoutY(button.getLayoutY()+previousImgWidth+(2*ButtonOFFSET));
        }
    }
    
    public void relocateCreditCardButton(Button b){
        creditCardButton.setLayoutX(ButtonOFFSET);
        creditCardButton.setLayoutY(MoneyButtons.get(MoneyButtons.size()-1).getLayoutY()+MoneyButtons.get(MoneyButtons.size()-1).getPrefHeight()+20);
    }
    
    public ArrayList<Button> getMoneyButton(){
        return MoneyButtons;
    }
}
