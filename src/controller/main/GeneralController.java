package controller.main;

import controller.DistributeurController;
import controller.ToolbarController;
import java.sql.Connection;
import model.CRUD.EarnedCRUD;
import view.GeneralView;

public class GeneralController {
    
    private final Connection connection;
    
    private final GeneralView generalView;
    private final DistributeurController distributeurController;
    private final ToolbarController toolBarController;
    private final EarnedCRUD earnedCrud;
    
    public GeneralController(Connection connection){
        this.connection=connection;
        distributeurController=new DistributeurController(this.connection, this);
        toolBarController=new ToolbarController(connection, distributeurController);
        earnedCrud=new EarnedCRUD(connection);
        
        generalView=new GeneralView(distributeurController.getDistributeurView(), distributeurController.getAllSandwichViews(), toolBarController.getToolBarView());
        generalView.getRestCoverView().getRestView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e-> disposeRest());
        generalView.getMoneyEarnedView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e->removeEarned());
    }
    
    public void removeEarned(){
        earnedCrud.resetEarned();
        generalView.getMoneyEarnedView().setVisible(false);
        generalView.getMoneyCoverView().CloseMoneyCover();
    }
    
    private void disposeRest(){
        distributeurController.getDistributeurView().setLabelText("0000");
        generalView.getRestCoverView().CloseRestCover();
        toolBarController.setSum(0);
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
    
    public void openMoneyCover(){
        if(earnedCrud.getEarned()>0){
            generalView.getMoneyEarnedView().setVisible(true);
        }else{
            generalView.getMoneyEarnedView().setVisible(false);
        }
        generalView.getMoneyCoverView().OpenMoneyCover();
    }
    
    public ToolbarController getToolBarController(){
        return toolBarController;
    }
}
