package controller;

import controller.main.GeneralController;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import model.CRUD.BoissonCRUD;
import model.CRUD.SandwichCRUD;
import model.CRUD.StockBoissonCRUD;
import model.CRUD.StockSandwichCRUD;
import model.Sandwich;
import model.StockSandwich;
import view.DistributeurView;
import view.SandwichView;

public class DistributeurController {
    
    private final Connection connection;
    private final StockSandwichCRUD stockSandwichCRUD;
    private final StockBoissonCRUD stockBoissonCRUD;
    private final SandwichCRUD sandwichCRUD;
    private final BoissonCRUD boissonCRUD;
    private final DistributeurView distributeurView;
    private ArrayList<ArrayList<SandwichController>> sandwichControllers;
    private final GeneralController generalController;
    
    public DistributeurController(Connection connection, GeneralController generalController){
        this.connection=connection;
        this.generalController=generalController;
        distributeurView=new DistributeurView();
        stockBoissonCRUD=new StockBoissonCRUD(this.connection);
        stockSandwichCRUD=new StockSandwichCRUD(this.connection);
        sandwichCRUD=new SandwichCRUD(this.connection);
        boissonCRUD=new BoissonCRUD(this.connection);
        
        initController();
    }
    
    private void initController(){
        sandwichControllers=new ArrayList<ArrayList<SandwichController>>();
        
        ArrayList<StockSandwich> lst=new ArrayList<StockSandwich>();
        lst=stockSandwichCRUD.getAllStock();
        int i=0;
        for(StockSandwich st:lst){
            sandwichControllers.add(new ArrayList<SandwichController>());
            for(int j=0; j<st.getQuantity(); j++){
                Sandwich s=sandwichCRUD.getSandwichByID(st.getID());
                sandwichControllers.get(i).add(new SandwichController(s, connection, this));
            }
            i++;
        }
        
        ArrayList<Button> buttons=distributeurView.getButtons();
        for(Button b:buttons){
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    buttonClicked((Button) t.getSource());
                }
            });
        }
    }
    
    private void resetStyle(Button btn, String style){
        try {
            Thread.sleep(1000);
            btn.setStyle(style);
        } catch (InterruptedException ex) {
            Logger.getLogger(DistributeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void buttonClicked(Button btn){
        int[][] buttonsPositions=distributeurView.getButtonsPositions();
        if(btn.getLayoutY()==buttonsPositions[0][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(0);
            if(l.size()==0){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                l.get(l.size()-1).fallSandwich();
            }
        }else if(btn.getLayoutY()==buttonsPositions[1][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(1);
            if(l.size()==0){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                l.get(l.size()-1).fallSandwich();
            }
        }else if(btn.getLayoutY()==buttonsPositions[2][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[3][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[4][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[5][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[6][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[7][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[8][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[9][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[10][1]){
            
        }else if(btn.getLayoutY()==buttonsPositions[11][1]){
            
        }
    }
    
    public void refreshStockSandwich(){
        initController();
        generalController.refresh();
    }
    
    public DistributeurView getDistributeurView(){
        return distributeurView;
    }
    
    public ArrayList<List<SandwichView>> getAllSandwichViews(){
        ArrayList<List<SandwichView>> lsv=new ArrayList<List<SandwichView>>();
        int i=0;
        for(List<SandwichController> ls:sandwichControllers){
            lsv.add(new ArrayList<SandwichView>());
            for(SandwichController sv:ls){
                lsv.get(i).add(sv.getSandwichView());
            }
            i++;
        }
        return lsv;
    }
    
    public GeneralController getGeneralController(){
        return generalController;
    }
}
