package uiContainers;

import Player.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DrawPanels extends VBox implements Drawable {

    ImageView imagePanel;
    Text namePanel;
    private Player p;

    public DrawPanels(Player p){
        this.p = p;


    }


    @Override
    public void fixSize() {
        setStyle(String.format("-fx-border-color: black;"
                + "-fx-background-size: %.0f %.0f; -fx-background-color: #FFFFFF;-fx-border-width: 0 0 5 0;", getWidth(), getHeight()));
    }

    @Override
    public void draw() {

    }

}
