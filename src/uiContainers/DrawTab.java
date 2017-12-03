package uiContainers;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class DrawTab extends BorderPane implements Drawable {


    public DrawTab(){
        setVisible(false);
        setMaxWidth(500);
        setMaxHeight(700);

    }

    public void display(DrawPanel dp){
        if(isVisible()) return;
        setVisible(true);
        Path path = new Path();
        path.getElements().add(new MoveTo(dp.getLayoutX()-getLayoutX()-300,dp.getLayoutY()+175));
        path.getElements().add(new HLineTo(dp.getLayoutX()+dp.getWidth()));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(400));
        pathTransition.setPath(path);
        pathTransition.setNode(this);
        pathTransition.setCycleCount(1);
        pathTransition.play();
    }

    public void hide(DrawPanel dp){
        if(!isVisible()) return;
        Path path = new Path();
        path.getElements().add(new MoveTo(dp.getLayoutX()+dp.getWidth(),dp.getLayoutY()+175));
        path.getElements().add(new HLineTo(dp.getLayoutX()-getLayoutX()-300));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(400));
        pathTransition.setPath(path);
        pathTransition.setNode(this);
        pathTransition.setCycleCount(1);
        pathTransition.play();
        pathTransition.setOnFinished(ae ->{
            setVisible(false);
        });
    }



    @Override
    public void fixSize() {

    }

    @Override
    public void draw() {

    }

}
