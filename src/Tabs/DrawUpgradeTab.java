package Tabs;

import Upgrades.Upgrade;
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

public class DrawUpgradeTab extends DrawTab {

    private Text title;
    private List<Upgrade> upgradeList = new ArrayList<>();

    public DrawUpgradeTab(ArrayList<Upgrade> list){
        super();
        this.upgradeList = list;
        this.title = new Text("Upgrades");

        title.setStyle("-fx-font-size: 32px;");
        title.setTextAlignment(TextAlignment.CENTER);

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(15,0,5,0));
        hb.setStyle("-fx-border-color: black; -fx-border-width: 0 0 5 0");
        VBox vb = new VBox();
        GridPane gp = new GridPane();


        for(int i = 0; i < upgradeList.size(); i++){

            HBox hbUpgrade = new HBox();

            if(upgradeList.get(i).isUnlocked() && !upgradeList.get(i).isBought()) {
                ImageView img = upgradeList.get(i).getImage();
                img.setFitWidth(41);
                img.setFitHeight(41);
                hbUpgrade.getChildren().add(img);
                hbUpgrade.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white");
                gp.add(hbUpgrade, i%11, i/11);
            }

        }


        hb.getChildren().add(title);
        vb.getChildren().addAll(hb,gp);

        setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-background-color: white;");
        setCenter(vb);


    }

}
