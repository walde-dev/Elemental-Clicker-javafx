package JavaFX_Stages;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public final int GAME_WIDTH = 700;
    public final int GAME_HEIGHT = 490;

    long AMOUNT_OF_CLICKS = 0;
    long GAIN_PER_CLICK = 1;


    Text textClicks;
    ImageView ivCoin;
    Image imageCoin;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Elemental Clicker");
        primaryStage.setResizable(false);

        StackPane root = new StackPane();
        root.setId("background-neutral");

        Scene mainScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
        mainScene.getStylesheets().add(Main.class.getResource("/images/styles.css").toExternalForm());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                refreshClicks();
            }
        }, 1000, 10);


        Button buttonClick = new Button();
        buttonClick.setText("Click");
        buttonClick.setId("shiny-orange");
        buttonClick.setTranslateX(0);
        buttonClick.setTranslateY(GAME_HEIGHT/2.3);
        buttonClick.setPrefSize(GAME_WIDTH/1.1,GAME_HEIGHT/9);
        buttonClick.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.5 * buttonClick.getPrefHeight())));
        buttonClick.setOnAction((event) -> {
            click();
        });


        Button buttonShop = new Button();
        buttonShop.setText("Shop");
        buttonShop.setId(buttonClick.getId());
        buttonShop.setTranslateX(GAME_WIDTH/2.5);
        buttonShop.setTranslateY(-GAME_HEIGHT/2.5);


        Button buttonOptions = new Button();
        buttonOptions.setText("Options");
        buttonOptions.setId(buttonClick.getId());
        buttonOptions.setTranslateX(buttonShop.getTranslateX());
        buttonOptions.setTranslateY(buttonShop.getTranslateY()+50);



        Image imageBackgroundClicks = new Image("images/background_clicks.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(imageBackgroundClicks);
        iv2.setFitWidth(145);
        iv2.setPreserveRatio(true);
        iv2.setTranslateX(-GAME_WIDTH/3);
        iv2.setTranslateY(-GAME_HEIGHT/2.5);
        iv2.setSmooth(true);
        iv2.setCache(true);


        textClicks = new Text(String.valueOf(AMOUNT_OF_CLICKS));
        textClicks.setTranslateX(iv2.getTranslateX());
        textClicks.setTranslateY(iv2.getTranslateY());
        textClicks.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.3 * iv2.getFitWidth())));

        imageCoin = new Image("images/coin.png");
        ivCoin = new ImageView();
        ivCoin.setImage(imageCoin);
        ivCoin.setTranslateY(textClicks.getTranslateY());
        ivCoin.setTranslateX(textClicks.getTranslateX()-48);
        ivCoin.setFitWidth(25);
        ivCoin.setPreserveRatio(true);
        ivCoin.setSmooth(true);
        ivCoin.setCache(true);

        root.getChildren().addAll(buttonClick, iv2, textClicks, ivCoin, buttonShop, buttonOptions);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    void click(){
        AMOUNT_OF_CLICKS+=GAIN_PER_CLICK;

    }

    void refreshClicks(){
        textClicks.setText(String.valueOf(AMOUNT_OF_CLICKS));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
