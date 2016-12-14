package view;

import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GeneralView extends Pane{
    private LeftGlareView leftGlareView;
    private RightGlareView rightGlareView;
    private ScreenGlareView screenGlareView;
    private DistributeurView distributeurView;
    private LeftCoverView leftCoverView;
    private RightCoverView rightCoverView;
    
    private ArrayList<SandwichView> sandwichViews;
    
    private final TranslateTransition leftCoverTT;
    private final TranslateTransition rightCoverTT;
    
    private final int[][] sandwitchPositions;
    
    public GeneralView(DistributeurView distributeurView, ArrayList<SandwichView> sandwichViews){
        sandwitchPositions=new int[][]{
            {237, 103}, {332, 103}, {427, 103},
            {237, 182}, {332, 182}, {427, 182},
            {237, 255}, {332, 255}, {427, 255},
            {237, 331}, {332, 331}, {427, 331},
            {237, 407}, {332, 407}, {427, 407},
            {237, 483}, {332, 483}, {427, 483},
        };
        
        leftGlareView=new LeftGlareView();
        rightGlareView=new RightGlareView();
        screenGlareView=new ScreenGlareView();
        leftCoverView=new LeftCoverView();
        rightCoverView=new RightCoverView();
        
        this.distributeurView=distributeurView;
        this.sandwichViews=sandwichViews;
        
        int i=0;
        for(SandwichView sv:sandwichViews){
            sv.setX(sandwitchPositions[i][0]);
            sv.setY(sandwitchPositions[i][1]);
            sv.setOpacity(0.65);
            i++;
        }
//        this.stockSandwichs=stockSandwichs;
//        this.stockBoissons=stockBoissons;
        
//        for(StockSandwich s : this.stockSandwichs){
//            switch (s.getSandwichName()){
//                case "sandwichBoeuf":
//                    for(int i=0; i<s.getQuantity(); i++){
//                        SandwichBoeufView sbv=new SandwichBoeufView();
//                        sbv.setX(sandwitchPositions[i][0]);
//                        sbv.setY(sandwitchPositions[i][1]);
//                        sbv.setOpacity(0.65);
//                        sandwichBoeufViews.add(sbv);
//                    }
//                    break;
//                case "paniniOmelette":
//                    for(int i=0; i<s.getQuantity(); i++){
//                        PaniniOmeletteView pv=new PaniniOmeletteView();
//                        pv.setX(sandwitchPositions[i+3][0]);
//                        pv.setY(sandwitchPositions[i+3][1]);
//                        pv.setOpacity(0.6);
//                        paniniOmeletteViews.add(pv);
//                    }
//                    break;
//                case "c":
//                    for(int i=0; i<s.getQuantity(); i++){
//                        
//                    }
//                    break;
//                case "d":
//                    for(int i=0; i<s.getQuantity(); i++){
//                        
//                    }
//                    break;
//                case "e":
//                    for(int i=0; i<s.getQuantity(); i++){
//                        
//                    }
//                    break;
//                case "f":
//                    for(int i=0; i<s.getQuantity(); i++){
//                        
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
        
        getChildren().add(this.distributeurView);
        getChildren().addAll(this.sandwichViews);
        getChildren().addAll(leftGlareView, rightGlareView, screenGlareView, leftCoverView, rightCoverView);

        
        leftCoverTT=new TranslateTransition();
        leftCoverTT.setDuration(Duration.millis(500));
        leftCoverTT.setNode(leftCoverView);
        
        rightCoverTT=new TranslateTransition();
        rightCoverTT.setDuration(Duration.millis(500));
        rightCoverTT.setNode(rightCoverView);
    }
    
    boolean rightCoverIsOpen=false;
    public void OpenCloseRightCover(){
        if(rightCoverIsOpen){
            rightCoverTT.setToY(0);
            rightCoverTT.play();
            rightCoverIsOpen=false;
        }else{
            rightCoverTT.setToY(-45);
            rightCoverTT.play();
            rightCoverIsOpen=true;
        }
    }
    
    boolean leftCoverIsOpen=false;
    public void OpenCloseLeftCover(){
        if(leftCoverIsOpen){
            leftCoverTT.setToY(0);
            leftCoverTT.play();
            leftCoverIsOpen=false;
        }else{
            leftCoverTT.setToY(-45);
            leftCoverTT.play();
            leftCoverIsOpen=true;
        }
    }
    
}
