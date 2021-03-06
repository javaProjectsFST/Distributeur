package controller;

import controller.main.GeneralController;
import controller.main.MainClass;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Boisson;
import model.CRUD.BoissonCRUD;
import model.CRUD.EarnedCRUD;
import model.CRUD.SandwichCRUD;
import model.CRUD.StockBoissonCRUD;
import model.CRUD.StockSandwichCRUD;
import model.Sandwich;
import model.StockBoisson;
import model.StockSandwich;
import view.BoissonView;
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
    private ArrayList<ArrayList<BoissonController>> boissonControllers;
    private final GeneralController generalController;
    private final EarnedCRUD earnedCrud;
    private boolean sandwichBoxFull=false;
    private boolean boissonBoxFull=false;
    
    public DistributeurController(Connection connection, GeneralController generalController){
        this.connection=connection;
        this.generalController=generalController;
        distributeurView=new DistributeurView();
        stockBoissonCRUD=new StockBoissonCRUD(this.connection);
        stockSandwichCRUD=new StockSandwichCRUD(this.connection);
        sandwichCRUD=new SandwichCRUD(this.connection);
        boissonCRUD=new BoissonCRUD(this.connection);
        earnedCrud=new EarnedCRUD(connection);
        
        initController();
    }
    
    public void resetScreen(){
        try {
            Thread.sleep(2000);
            distributeurView.getScreenErrorMessage().setVisible(false);
            distributeurView.getScreenSuccesMessage().setVisible(false);
            distributeurView.getScreenWaitMessage().setVisible(false);
            for(Button b:distributeurView.getScreenButtons()){
                b.setVisible(false);
            }
            distributeurView.getScrennMessageOne().setVisible(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(DistributeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int count=0;
    String code="";
    boolean cardStatus=false;
    private void screenButtonClicked(Button b){
        count++;
        code+=b.getText();
        if(count==5){
            for(Button bt:distributeurView.getScreenButtons()){
                bt.setVisible(false);
            }
            if(code.equals(MainClass.CARDCODE)){
                distributeurView.getScreenWaitMessage().setVisible(true);
                this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenWaitMessage());
                cardStatus=true;
            }else{
                cardStatus=false;
                distributeurView.getScreenErrorMessage().setVisible(true);
                this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenErrorMessage());
                new Thread(()->resetScreen()).start();
            }
            code="";
            count=0;
        }
    }
    
    public void initController(){
        sandwichControllers=new ArrayList<ArrayList<SandwichController>>();
        boissonControllers=new ArrayList<ArrayList<BoissonController>>();
        ArrayList<Button> screenButtons=distributeurView.getScreenButtons();
        for(Button b:screenButtons){
            b.setOnAction(e->screenButtonClicked(b));
        }
        
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
        
        ArrayList<StockBoisson> lsb=new ArrayList<StockBoisson>();
        lsb=stockBoissonCRUD.getAllStock();
        int j=0;
        for(StockBoisson sb:lsb){
            boissonControllers.add(new ArrayList<BoissonController>());
            for(int v=0; v<sb.getQuantity(); v++){
                Boisson b=boissonCRUD.getBoissonByID(sb.getID());
                boissonControllers.get(j).add(new BoissonController(b, connection, this));
            }
            j++;
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
    
    public boolean getSandwichBoxFull(){
        return sandwichBoxFull;
    }
    
    public void setSandwichBoxFull(boolean b){
        sandwichBoxFull=b;
    }
    
    private void buttonClicked(Button btn){
        int[][] buttonsPositions=distributeurView.getButtonsPositions();
        if(btn.getLayoutY()==buttonsPositions[0][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(0);
            if(l.size()==0 || sandwichBoxFull || (generalController.getToolBarController().getSum()<l.get(l.size()-1).getSandwich().getPrice() && !cardStatus)){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                sandwichBoxFull=true;
                l.get(l.size()-1).fallSandwich();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getSandwich().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getSandwich().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[1][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(1);
            if(l.size()==0 || sandwichBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getSandwich().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                sandwichBoxFull=true;
                l.get(l.size()-1).fallSandwich();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getSandwich().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getSandwich().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[2][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(2);
            if(l.size()==0 || sandwichBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getSandwich().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                sandwichBoxFull=true;
                l.get(l.size()-1).fallSandwich();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getSandwich().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getSandwich().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[3][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(3);
            if(l.size()==0 || sandwichBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getSandwich().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                sandwichBoxFull=true;
                l.get(l.size()-1).fallSandwich();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getSandwich().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getSandwich().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[4][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(4);
            if(l.size()==0 || sandwichBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getSandwich().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                sandwichBoxFull=true;
                l.get(l.size()-1).fallSandwich();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getSandwich().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getSandwich().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[5][1]){
            ArrayList<SandwichController> l=sandwichControllers.get(5);
            if(l.size()==0 || sandwichBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getSandwich().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                sandwichBoxFull=true;
                l.get(l.size()-1).fallSandwich();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getSandwich().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getSandwich().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[6][1]){
            ArrayList<BoissonController> l=boissonControllers.get(0);
            if(l.size()==0 || boissonBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getBoisson().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                boissonBoxFull=true;
                l.get(l.size()-1).fallBoisson();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getBoisson().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getBoisson().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[7][1]){
            ArrayList<BoissonController> l=boissonControllers.get(1);
            if(l.size()==0 || boissonBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getBoisson().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                boissonBoxFull=true;
                l.get(l.size()-1).fallBoisson();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getBoisson().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getBoisson().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[8][1]){
            ArrayList<BoissonController> l=boissonControllers.get(2);
            if(l.size()==0 || boissonBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getBoisson().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                boissonBoxFull=true;
                l.get(l.size()-1).fallBoisson();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getBoisson().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getBoisson().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[9][1]){
            ArrayList<BoissonController> l=boissonControllers.get(3);
            if(l.size()==0 || boissonBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getBoisson().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                boissonBoxFull=true;
                l.get(l.size()-1).fallBoisson();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getBoisson().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getBoisson().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[10][1]){
            ArrayList<BoissonController> l=boissonControllers.get(4);
            if(l.size()==0 || boissonBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getBoisson().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                boissonBoxFull=true;
                l.get(l.size()-1).fallBoisson();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getBoisson().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getBoisson().getPrice());
            }
        }else if(btn.getLayoutY()==buttonsPositions[11][1]){
            ArrayList<BoissonController> l=boissonControllers.get(5);
            if(l.size()==0 || boissonBoxFull || generalController.getToolBarController().getSum()<l.get(l.size()-1).getBoisson().getPrice() && !cardStatus){
                String style=btn.getStyle();
                btn.setStyle(style+"; -fx-background-color: #f5a0a0;");
                new Thread(()->resetStyle(btn, style)).start();
            }else{
                boissonBoxFull=true;
                l.get(l.size()-1).fallBoisson();
                if(generalController.getToolBarController().getSum()!=0){
                    double r=generalController.getToolBarController().getSum()-l.get(l.size()-1).getBoisson().getPrice();
                    int rest=(int)r;
                    if(rest>0){
                        distributeurView.setLabelText(String.valueOf(rest));
                        generalController.getGeneralView().getRestCoverView().OpenRestCover();
                    }
                    generalController.getToolBarController().setSum(0);
                    distributeurView.setLabelText(String.valueOf(rest));
                }else{
                    distributeurView.getScreenWaitMessage().setVisible(false);
                    distributeurView.getScreenSuccesMessage().setVisible(true);
                    this.generalController.getGeneralView().getChildren().add(distributeurView.getScreenSuccesMessage());
                    new Thread(()->resetScreen()).start();
                    generalController.getToolBarController().setSum(0);
                    cardStatus=false;
                }
                earnedCrud.addEarned(l.get(l.size()-1).getBoisson().getPrice());
            }
        }
    }
    
    public void refreshStockSandwich(){
        initController();
        generalController.refresh();
    }
    
    public void refreshStockBoisson(){
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
    
    public ArrayList<List<BoissonView>> getAllBoissonViews(){
        ArrayList<List<BoissonView>> lsv=new ArrayList<List<BoissonView>>();
        int i=0;
        for(List<BoissonController> ls:boissonControllers){
            lsv.add(new ArrayList<BoissonView>());
            for(BoissonController sv:ls){
                lsv.get(i).add(sv.getBoissonView());
            }
            i++;
        }
        return lsv;
    }
    
    public GeneralController getGeneralController(){
        return generalController;
    }
    
    public boolean getBoissonBoxFull(){
        return boissonBoxFull;
    }
    
    public void setBoissonBoxFull(boolean b){
        boissonBoxFull=b;
    }
}
