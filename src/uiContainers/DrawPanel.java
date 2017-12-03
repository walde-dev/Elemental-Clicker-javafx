package uiContainers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DrawPanel extends VBox implements Drawable {

    ImageView imagePanel;
    Text namePanel;
    DrawTab tab;

    public DrawPanel(String image, String name, DrawTab tab){
        this.imagePanel = new ImageView(new Image(image));
        this.namePanel = new Text(name);
        this.tab = tab;
        uiContainers.DrawMaster.getDrawMaster().add(this);

        imagePanel.setFitHeight(50);
        imagePanel.setFitWidth(50);

        namePanel.setStyle("-fx-font-size: 22px;");

        setAlignment(Pos.CENTER);
        setPadding(new Insets(8));
        setSpacing(2);
        heightProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue + "x " + newValue);
            setPrefWidth(newValue.doubleValue());
        });
        setStyle("-fx-border-color: black; -fx-background-color: white;-fx-background-radius:11; -fx-border-radius: 10; -fx-border-width: 6;");
        getChildren().addAll(imagePanel, namePanel);


        setOnMouseClicked(ae ->{
            if(!tab.isVisible()){
                tab.display(this);
            }else{
                tab.hide(this);
            }
        });

    }


    @Override
    public void fixSize() {

    }

    @Override
    public void draw() {

    }

}
