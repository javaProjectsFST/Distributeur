package view;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurveTo;
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
    
    private ArrayList<List<SandwichView>> sandwichViews;
    
    private final int[][] Positions;
    private final ArrayList<List<Point>> sandwichPositions;
    
    public GeneralView(DistributeurView distributeurView, ArrayList<List<SandwichView>> sandwichViews){
        Positions=new int[][]{
            {237, 103}, {332, 103}, {427, 103},
            {237, 182}, {332, 182}, {427, 182},
            {237, 255}, {332, 255}, {427, 255},
            {237, 331}, {332, 331}, {427, 331},
            {237, 407}, {332, 407}, {427, 407},
            {237, 483}, {332, 483}, {427, 483},
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
        
        this.distributeurView=distributeurView;
        
        initView(sandwichViews);
    }
    
    public void initView(ArrayList<List<SandwichView>> sandwichViews){
        this.sandwichViews=sandwichViews;
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
        
        getChildren().clear();
        getChildren().add(this.distributeurView.getBgImgView());
        for(List<SandwichView> lsv:sandwichViews){
            getChildren().addAll(lsv);
        }
        getChildren().add(this.distributeurView.getFgImgView());
        getChildren().addAll(this.distributeurView.getButtons());
        getChildren().addAll(leftGlareView, rightGlareView, screenGlareView, leftCoverView, rightCoverView);
        
        leftCoverView.CloseLeftCover();
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
        sView.setTranslateX(327-sView.getX());
        sView.setTranslateY(678-sView.getY());
        sView.setOpacity(1);
        leftCoverView.OpenLeftCover();
    }
}
