package controller;

import java.sql.Connection;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.input.MouseEvent;
import model.CRUD.MoneyCRUD;
import model.Money;
import view.ToolBarView;

public class ToolbarController {
    private final ToolBarView toolBarView;
    private ArrayList<Money> moneys;
    private final MoneyCRUD moneyCrud;
    private final Connection connection;
    
    public ToolbarController(Connection connection){
        this.connection=connection;
        moneyCrud=new MoneyCRUD(connection);
        moneys=moneyCrud.getAllMoney();
        toolBarView=new ToolBarView(moneys);
        initComponent();
    }
    
    public void initComponent(){
        ArrayList<Button> moneyButtons=toolBarView.getMoneyButton();
        for(Button b:moneyButtons){
            setMoneyButtonsMouseListener(b);
        }
    }
    
    public void setMoneyButtonsMouseListener(Button button){
        Light.Point p=new Light.Point();
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                p.setX(button.getLayoutX() - mouseEvent.getSceneX());
                p.setY(button.getLayoutY() - mouseEvent.getSceneY());
                button.setCursor(Cursor.MOVE);
            }
        });
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setCursor(Cursor.HAND);
                toolBarView.relocateMoneyButton(((Button)mouseEvent.getSource()));
            }
        });
        button.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setLayoutX(mouseEvent.getSceneX() + p.getX());
                button.setLayoutY(mouseEvent.getSceneY() + p.getY());
            }
        });
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setCursor(Cursor.HAND);
            }
        });
    }

    public ToolBarView getToolBarView() {
        return toolBarView;
    }

    public ArrayList<Money> getMoneys() {
        return moneys;
    }
}
