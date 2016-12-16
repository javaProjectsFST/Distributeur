package controller.main;

import controller.DistributeurController;
import controller.ToolbarController;
import java.sql.Connection;
import view.GeneralView;

public class GeneralController {
    
    private final Connection connection;
    
    private final GeneralView generalView;
    private final DistributeurController distributeurController;
    private final ToolbarController toolBarController;
    
    public GeneralController(Connection connection){
        this.connection=connection;
        distributeurController=new DistributeurController(this.connection, this);
        toolBarController=new ToolbarController(connection);
        
        generalView=new GeneralView(distributeurController.getDistributeurView(), distributeurController.getAllSandwichViews(), toolBarController.getToolBarView());
    }
    
    public void refresh(){
        generalView.initView(distributeurController.getAllSandwichViews());
    }
    
    public GeneralView getGeneralView(){
        return generalView;
    }
    
    public int fallSandwich(int type){
        return generalView.fallSandwich(type);
    }
}
