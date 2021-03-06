package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class GeneralView extends Pane{
    private LeftGlareView leftGlareView;
    private RightGlareView rightGlareView;
    private ScreenGlareView screenGlareView;
    private DistributeurView distributeurView;
    private LeftCoverView leftCoverView;
    private RightCoverView rightCoverView;
    private ToolBarView toolBarView;
    private MoneyCoverView moneyCoverView;
    private RestCoverView restCoverView;
    private ImageView moneyEarnedView;
    private Button reloadButton;
    
    private ArrayList<List<SandwichView>> sandwichViews;
    private ArrayList<List<BoissonView>> boissonViews;
    
    private final int[][] Positions;
    private final ArrayList<List<Point>> sandwichPositions;
    
    public GeneralView(DistributeurView distributeurView, ArrayList<List<SandwichView>> sandwichViews, ToolBarView toolBarView, ArrayList<List<BoissonView>> boissonView){
        Positions=new int[][]{
            {337, 103}, {432, 103}, {527, 103},
            {337, 182}, {432, 182}, {527, 182},
            {337, 255}, {432, 255}, {527, 255},
            {337, 331}, {432, 331}, {527, 331},
            {337, 407}, {432, 407}, {527, 407},
            {337, 483}, {432, 483}, {527, 483},
        };
        sandwichPositions=new ArrayList<List<Point>>();
        for(int i=0; i<18; i+=3){
            List<Point> l=new ArrayList<Point>();
            Point p=new Point(); p.setX(Positions[i][0]); p.setY(Positions[i][1]);
            l.add(p);
            p=new Point(); p.setX(Positions[i+1][0]); p.setY(Positions[i+1][1]);
            l.add(p);
            p=new Point(); p.setX(Positions[i+2][0]); p.setY(Positions[i+2][1]);
            l.add(p);
            sandwichPositions.add(l);
        }
        
        leftGlareView=new LeftGlareView();
        rightGlareView=new RightGlareView();
        screenGlareView=new ScreenGlareView();
        leftCoverView=new LeftCoverView();
        rightCoverView=new RightCoverView();
        moneyCoverView=new MoneyCoverView();
        restCoverView=new RestCoverView();
        moneyEarnedView=new ImageView(new Image("resources/pieces/allPieces.png"));
        moneyEarnedView.setVisible(false);
        reloadButton=new Button("Reload");
        reloadButton.setStyle("-fx-font: 10 arial");
        reloadButton.setPrefSize(60, 30);
        reloadButton.setMinSize(60, 30);
        reloadButton.setMaxSize(60, 30);
        reloadButton.setLayoutX(960);
        reloadButton.setLayoutY(620);
        
        this.distributeurView=distributeurView;
        this.toolBarView=toolBarView;
        
        initView(sandwichViews, boissonView);
    }
    
    public RightCoverView getRightCoverView(){
        return rightCoverView;
    }
    
    public void initView(ArrayList<List<SandwichView>> sandwichView, ArrayList<List<BoissonView>> boissonView){
        this.sandwichViews=sandwichView;
        this.boissonViews=boissonView;
        int i=0;
        for(List<SandwichView> lsv:sandwichViews){
            for(SandwichView s: lsv){
                s.setX(sandwichPositions.get(s.getType()).get(i).getX());
                s.setY(sandwichPositions.get(s.getType()).get(i).getY());
                s.setOpacity(0.65);
                i++;
            }
            i=0;
        }
        
        int j=0;
        for(List<BoissonView> lsb:boissonViews){
            for(BoissonView b: lsb){
                int minusY=35;
                if(b.getType()>3){
                    minusY=15;
                }
                b.setX(sandwichPositions.get(b.getType()).get(j).getX()+340);
                b.setY(sandwichPositions.get(b.getType()).get(j).getY()-minusY);
                b.setOpacity(0.65);
                j++;
            }
            j=0;
        }
        
        getChildren().clear();
        
        this.distributeurView.setViewX(toolBarView.getPrefWidth());
        this.leftGlareView.setLayoutX(toolBarView.getPrefWidth());
        this.rightGlareView.setLayoutX(toolBarView.getPrefWidth());
        this.leftCoverView.setLayoutX(toolBarView.getPrefWidth());
        this.rightCoverView.setLayoutX(toolBarView.getPrefWidth());
        this.screenGlareView.setLayoutX(toolBarView.getPrefWidth());
        this.moneyCoverView.setLayoutX(toolBarView.getPrefWidth());
        this.restCoverView.setLayoutX(toolBarView.getPrefWidth());
        this.restCoverView.getRestView().setLayoutX(toolBarView.getPrefWidth());
        this.moneyEarnedView.setLayoutX(toolBarView.getPrefWidth());

        
        getChildren().add(this.distributeurView.getBgImgView());
        for(List<SandwichView> lsv:sandwichViews){
            getChildren().addAll(lsv);
        }
        for(List<BoissonView> lsb:boissonViews){
            getChildren().addAll(lsb);
        }
        getChildren().add(this.distributeurView.getFgImgView());
        getChildren().add(moneyEarnedView);
        getChildren().addAll(this.distributeurView.getButtons());
        getChildren().addAll(this.distributeurView.getScrennMessageOne());
        getChildren().add(reloadButton);
        getChildren().addAll(leftGlareView, rightGlareView, leftCoverView, rightCoverView, moneyCoverView, restCoverView.getRestView(), restCoverView);
        getChildren().add(this.distributeurView.getSumLabel());
        getChildren().add(toolBarView);
        leftCoverView.CloseLeftCover();
    }
    
    public Button getReloadButton(){
        return reloadButton;
    }
    
    public ImageView getMoneyEarnedView(){
        return moneyEarnedView;
    }
    
    public int fallBoisson(int type){
        Path path=new Path();
        ImageView sView;
        try{
            sView=boissonViews.get(type).get(boissonViews.get(type).size()-1);
        }catch(ArrayIndexOutOfBoundsException e){
            return -1;
        }
        path.getElements().add(new MoveTo(sView.getX()+(sView.getImage().getWidth()/2), sView.getY()+(sView.getImage().getHeight()/2)));
        
        LineTo l=new LineTo();
        l.setX(sView.getX()+(sView.getImage().getWidth()/2));
        l.setY(640);
        path.getElements().add(l);
        
        PathTransition pathTransition=new PathTransition();
        pathTransition.setDuration(Duration.millis(700));
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setNode(sView);
        
        RotateTransition r=new RotateTransition(Duration.millis(650), sView);
        r.setByAngle(360);
        r.setCycleCount(Timeline.INDEFINITE);
        
        ParallelTransition parallelTransition=new ParallelTransition();
        parallelTransition.getChildren().addAll(pathTransition, r);
        parallelTransition.play();
        pathTransition.setOnFinished(e->boissonInBox(parallelTransition, sView));
        return 0;
    }
    
    public int fallSandwich(int type){
        Path path=new Path();
        ImageView sView;
        try{
            sView=sandwichViews.get(type).get(sandwichViews.get(type).size()-1);
        }catch(ArrayIndexOutOfBoundsException e){
            return -1;
        }
        path.getElements().add(new MoveTo(sView.getX()+(sView.getImage().getWidth()/2), sView.getY()+(sView.getImage().getHeight()/2)));
        
        LineTo l=new LineTo();
        l.setX(sView.getX()+(sView.getImage().getWidth()/2));
        l.setY(640);
        path.getElements().add(l);
        
        PathTransition pathTransition=new PathTransition();
        pathTransition.setDuration(Duration.millis(700));
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setNode(sView);
        
        RotateTransition r=new RotateTransition(Duration.millis(650), sView);
        r.setByAngle(360);
        r.setCycleCount(Timeline.INDEFINITE);
        
        ParallelTransition parallelTransition=new ParallelTransition();
        parallelTransition.getChildren().addAll(pathTransition, r);
        parallelTransition.play();
        pathTransition.setOnFinished(e->sandwichInBox(parallelTransition, sView));
        return 0;
    }
    
    private void sandwichInBox(ParallelTransition parallelTransition, ImageView sView){
        parallelTransition.stop();
        sView.setTranslateX(427-sView.getX());
        sView.setTranslateY(678-sView.getY());
        sView.setOpacity(1);
        leftCoverView.OpenLeftCover();
    }
    
    private void boissonInBox(ParallelTransition parallelTransition, ImageView sView){
        parallelTransition.stop();
        sView.setTranslateX(765-sView.getX());
        sView.setTranslateY(660-sView.getY());
        sView.setOpacity(1);
        RotateTransition r=new RotateTransition(Duration.millis(10), sView);
        r.setByAngle(90);
        r.setCycleCount(1);
        r.play();
        rightCoverView.OpenRightCover();
    }
    
    public MoneyCoverView getMoneyCoverView(){
        return moneyCoverView;
    }
    
    public RestCoverView getRestCoverView(){
        return restCoverView;
    }
}
