package controller;

import java.sql.Connection;
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
    
    public SandwichController(Sandwich sandwich, Connection connection){
        this.sandwich=sandwich;
        this.connection=connection;
        sandwichCRUD=new SandwichCRUD(this.connection);
        stockSandwichCRUD=new StockSandwichCRUD(connection);
        int type=-1;
        switch (sandwich.getSandwichName()){
            case "sandwichBoeuf":
                type=0;
                break;
            case "paniniOmelette":
                type=1;
                break;
            default:
                break;
        }
        sandwichView=new SandwichView(this.sandwich.getImgPath(), type);
    }
    
    public SandwichView getSandwichView(){
        return sandwichView;
    }
    
    public void sellSandwich(){
        if(stockSandwichCRUD.removeSandwich(sandwich.getID())){
            
        }
    }
}
