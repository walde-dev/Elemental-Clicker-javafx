package Tabs;

import Achievments.Achievement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uiContainers.DrawTab;

import java.util.ArrayList;
import java.util.List;

public class DrawAchievementTab extends DrawTab {

    private Text title;
    private List<Achievement> achievementList = new ArrayList<>();

    public DrawAchievementTab(ArrayList<Achievement> list){
        super();
        this.title = new Text("Achievements");
        this.achievementList = list;

        title.setStyle("-fx-font-size: 32px;");
        title.setTextAlignment(TextAlignment.CENTER);

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(15,0,5,0));
        hb.setStyle("-fx-border-color: black; -fx-border-width: 0 0 5 0");
        VBox vb = new VBox();
        GridPane gp = new GridPane();


        for(int i = 0; i < achievementList.size(); i++){

            HBox hbAchievement = new HBox();

            ImageView img = achievementList.get(i).getImage();
            img.setFitWidth(41);
            img.setFitHeight(41);
            hbAchievement.getChildren().add(img);
            hbAchievement.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white");
            gp.add(hbAchievement, i%11, i/11);


        }


        hb.getChildren().add(title);
        vb.getChildren().addAll(hb,gp);

        setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-background-color: white;");
        setCenter(vb);


    }


}
