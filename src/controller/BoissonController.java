package controller;

import java.sql.Connection;
import javafx.scene.input.MouseEvent;
import model.CRUD.BoissonCRUD;
import model.CRUD.StockBoissonCRUD;
import model.Boisson;
import view.BoissonView;

public class BoissonController {
    private final Connection connection;
    private BoissonView boissonView;
    private Boisson boisson;
    private BoissonCRUD boissonCRUD;
    private StockBoissonCRUD stockBoissonCRUD;
    private DistributeurController distributeurController;
    private int type=-1;
    
    public BoissonController(Boisson boisson, Connection connection, DistributeurController distributeurController){
        this.boisson=boisson;
        this.connection=connection;
        this.distributeurController=distributeurController;
        boissonCRUD=new BoissonCRUD(this.connection);
        stockBoissonCRUD=new StockBoissonCRUD(connection);
        switch (boisson.getBoissonName()){
            case "coke":
                type=0;
                break;
            case "water":
                type=1;
                break;
            case "fanta":
                type=2;
                break;
            case "cidre":
                type=3;
                break;
            case "petillante":
                type=4;
                break;
            case "boga":
                type=5;
                break;
            default:
                break;
        }
        boissonView=new BoissonView(this.boisson.getImgPath(), type);
        initComponenet();
    }
    
    public void initComponenet(){
        boissonView.addEventHandler(MouseEvent.MOUSE_CLICKED, e->dispose());
    }
    
    public int fallBoisson(){
        if(distributeurController.getGeneralController().fallBoisson(type)==-1){
            return -1;
        }
        stockBoissonCRUD.removeBoisson(boisson.getID());
        return 0;
    }
    
    public void dispose(){
        distributeurController.setBoissonBoxFull(false);
        distributeurController.refreshStockBoisson();
        distributeurController.getGeneralController().getGeneralView().getRightCoverView().CloseRightCover();
    }
    
    public BoissonView getBoissonView(){
        return boissonView;
    }
    
    public void sellBoisson(){
        if(stockBoissonCRUD.removeBoisson(boisson.getID())){
            
        }
    }
    
    public Boisson getBoisson(){
        return boisson;
    }
}
