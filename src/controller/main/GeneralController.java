package controller.main;

import controller.DistributeurController;
import java.sql.Connection;
import java.util.ArrayList;
import model.CRUD.StockBoissonCRUD;
import model.CRUD.StockSandwichCRUD;
import model.StockBoisson;
import model.StockSandwich;
import view.GeneralView;

public class GeneralController {
    
    private final Connection connection;
    
    private final GeneralView generalView;
    private final DistributeurController distributeurController;
    
    public GeneralController(Connection connection){
        this.connection=connection;
        distributeurController=new DistributeurController(this.connection, this);
        
        generalView=new GeneralView(distributeurController.getDistributeurView(), distributeurController.getAllSandwichViews());
    }
    
    public void refresh(){
        generalView.initView(distributeurController.getAllSandwichViews());
    }
    
    public GeneralView getGeneralPane(){
        return generalView;
    }
}
