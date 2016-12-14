package controller;

import java.sql.Connection;
import java.util.ArrayList;
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
    private ArrayList<SandwichController> sandwichControllers;
    
    public DistributeurController(Connection connection){
        this.connection=connection;
        distributeurView=new DistributeurView();
        stockBoissonCRUD=new StockBoissonCRUD(this.connection);
        stockSandwichCRUD=new StockSandwichCRUD(this.connection);
        sandwichCRUD=new SandwichCRUD(this.connection);
        boissonCRUD=new BoissonCRUD(this.connection);
        
        sandwichControllers=new ArrayList<SandwichController>();
        
        ArrayList<StockSandwich> lst=new ArrayList<StockSandwich>();
        lst=stockSandwichCRUD.getAllStock();
        for(StockSandwich st:lst){
            for(int i=0; i<st.getQuantity(); i++){
                Sandwich s=sandwichCRUD.getSandwichByID(st.getID());
                sandwichControllers.add(new SandwichController(s));
            }
        }
    }
    
    public DistributeurView getDistributeurView(){
        return distributeurView;
    }
    
    public ArrayList<SandwichView> getAllSandwichViews(){
        ArrayList<SandwichView> lsv=new ArrayList<SandwichView>();
        for(SandwichController s:sandwichControllers){
            lsv.add(s.getSandwichView());
        }
        return lsv;
    }
}
