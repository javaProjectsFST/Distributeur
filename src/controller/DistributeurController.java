package controller;

import controller.main.GeneralController;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
    private ArrayList<List<SandwichController>> sandwichControllers;
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
        sandwichControllers=new ArrayList<List<SandwichController>>();
        
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
}
