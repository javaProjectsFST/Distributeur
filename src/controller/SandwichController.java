package controller;

import java.sql.Connection;
import javafx.scene.input.MouseEvent;
import model.CRUD.SandwichCRUD;
import model.CRUD.StockSandwichCRUD;
import model.Sandwich;
import view.SandwichView;

public class SandwichController {
    private final Connection connection;
    private SandwichView sandwichView;
    private Sandwich sandwich;
    private SandwichCRUD sandwichCRUD;
    private StockSandwichCRUD stockSandwichCRUD;
    private DistributeurController distributeurController;
    private int type=-1;
    
    public SandwichController(Sandwich sandwich, Connection connection, DistributeurController distributeurController){
        this.sandwich=sandwich;
        this.connection=connection;
        this.distributeurController=distributeurController;
        sandwichCRUD=new SandwichCRUD(this.connection);
        stockSandwichCRUD=new StockSandwichCRUD(connection);
        switch (sandwich.getSandwichName()){
            case "sandwichBoeuf":
                type=0;
                break;
            case "paniniOmelette":
                type=1;
                break;
            case "PaniniBoeuf":
                type=2;
                break;
            case "PaniniSalami":
                type=3;
                break;
            case "SandwichOmelette":
                type=4;
                break;
            case "SandwichSalade":
                type=5;
                break;
            default:
                break;
        }
        sandwichView=new SandwichView(this.sandwich.getImgPath(), type);
        initComponenet();
    }
    
    public void initComponenet(){
        sandwichView.addEventHandler(MouseEvent.MOUSE_CLICKED, e->dispose());
    }
    
    public int fallSandwich(){
        if(distributeurController.getGeneralController().fallSandwich(type)==-1){
            return -1;
        }
        stockSandwichCRUD.removeSandwich(sandwich.getID());
        return 0;
    }
    
    public void dispose(){
        distributeurController.setSandwichBoxFull(false);
        distributeurController.refreshStockSandwich();
    }
    
    public SandwichView getSandwichView(){
        return sandwichView;
    }
    
    public void sellSandwich(){
        if(stockSandwichCRUD.removeSandwich(sandwich.getID())){
            
        }
    }
    
    public Sandwich getSandwich(){
        return sandwich;
    }
}
