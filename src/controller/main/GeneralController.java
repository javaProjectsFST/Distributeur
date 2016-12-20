package controller.main;

import controller.DistributeurController;
import controller.ToolbarController;
import java.io.File;
import java.sql.Connection;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.CRUD.EarnedCRUD;
import model.CRUD.StockBoissonCRUD;
import model.CRUD.StockSandwichCRUD;
import view.GeneralView;

public class GeneralController {
    
    private final Connection connection;
    
    private final GeneralView generalView;
    private final DistributeurController distributeurController;
    private final ToolbarController toolBarController;
    private final EarnedCRUD earnedCrud;
    private final StockBoissonCRUD stockBoissonCRUD;
    private final StockSandwichCRUD stockSandwichCRUD;
    
    public GeneralController(Connection connection){
        this.connection=connection;
        distributeurController=new DistributeurController(this.connection, this);
        toolBarController=new ToolbarController(connection, distributeurController);
        earnedCrud=new EarnedCRUD(connection);
        stockBoissonCRUD=new StockBoissonCRUD(connection);
        stockSandwichCRUD=new StockSandwichCRUD(connection);
        
        generalView=new GeneralView(distributeurController.getDistributeurView(), distributeurController.getAllSandwichViews(), toolBarController.getToolBarView(), distributeurController.getAllBoissonViews());
        generalView.getRestCoverView().getRestView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e-> disposeRest());
        generalView.getMoneyEarnedView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e->removeEarned());
        generalView.getMoneyCoverView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e->closeMoneyCover());
        generalView.getReloadButton().setOnAction(e->reload());
    }
    
    public void reload(){
        stockBoissonCRUD.initStock();
        stockSandwichCRUD.initStock();
        distributeurController.initController();
        this.refresh();
    }
    
    public void closeMoneyCover(){
        generalView.getMoneyCoverView().CloseMoneyCover();
        toolBarController.getToolBarView().getLoginButton().setVisible(true);
    }
    
    public void removeEarned(){
        earnedCrud.resetEarned();
        generalView.getMoneyEarnedView().setVisible(false);
    }
    
    private void disposeRest(){
        File audioFile=new File("src/resources/getMoneySound.mp3");
        Media audio=new Media(audioFile.toURI().toString());
        MediaPlayer audioPlayer=new MediaPlayer(audio);
        audioPlayer.play();
        audioPlayer.setOnEndOfMedia(new Thread(()->audioPlayer.dispose()));
        distributeurController.getDistributeurView().setLabelText("0000");
        generalView.getRestCoverView().CloseRestCover();
        toolBarController.setSum(0);
    }
    
    public void refresh(){
        generalView.initView(distributeurController.getAllSandwichViews(), distributeurController.getAllBoissonViews());
    }
    
    public GeneralView getGeneralView(){
        return generalView;
    }
    
    public int fallBoisson(int type){
        return generalView.fallBoisson(type);
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
