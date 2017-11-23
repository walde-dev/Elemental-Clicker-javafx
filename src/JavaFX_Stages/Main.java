package JavaFX_Stages;
import Buildings.Building;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.math.BigDecimal;
import java.util.*;

public class Main extends Application {

    public final int GAME_WIDTH = 1200;
    public final int GAME_HEIGHT = 490;

    public final double CLICK_WINDOW_TRANSLATE_X = -GAME_WIDTH/2.4;
    public final double CLICK_WINDOW_TRANSLATE_Y = -GAME_HEIGHT/2.5;

    public final double BUILDINGS_WINDOW_TRANSLATE_X = GAME_WIDTH/2.5;
    public final double BUILDINGS_WINDOW_TRANSLATE_Y = -GAME_HEIGHT/2.5;

    public long AMOUNT_OF_COINS = (long)Math.pow(10, 15);
    public long COINS_PER_SECOND = 0;
    public long GAIN_PER_CLICK = 1;



    Text textClicks, textCoinsPerSecond, farmCost, farmAmount, windmillCost, windmillAmount, farmProportion, windmillProportion;
    ImageView ivCoin;
    Image imageCoin;

    Building Farm = new Building("Farm", "images/farm.png", 10, 2, 0);
    Building Windmill = new Building("Windmill", "images/windmill.png", 150, 6, 0);

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Elemental Clicker");
        primaryStage.setResizable(false);

        Text textClick = new Text("+" + format(GAIN_PER_CLICK));
        textClick.setVisible(false);

        StackPane root = new StackPane();
        root.setId("background-neutral");
        root.setOnMouseClicked((MouseEvent e) ->{
            click();
        });

        Scene mainScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
        mainScene.getStylesheets().add(Main.class.getResource("/images/styles.css").toExternalForm());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                refreshGUI();
            }
        }, 900, 1);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                clickPerSecond();
            }
        }, 1000, 500);


        Image imageBackgroundClicks = new Image("images/background_clicks.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(imageBackgroundClicks);
        iv2.setFitWidth(175);
        iv2.setFitHeight(50);
        iv2.setTranslateX(CLICK_WINDOW_TRANSLATE_X);
        iv2.setTranslateY(CLICK_WINDOW_TRANSLATE_Y);
        iv2.setSmooth(true);
        iv2.setCache(true);

        ImageView ivCoinsPerSecond = new ImageView();
        ivCoinsPerSecond.setImage(imageBackgroundClicks);
        ivCoinsPerSecond.setFitWidth(145);
        ivCoinsPerSecond.setFitHeight(50);
        ivCoinsPerSecond.setTranslateX(iv2.getTranslateX());
        ivCoinsPerSecond.setTranslateY(iv2.getTranslateY()+ivCoinsPerSecond.getFitHeight()+20);
        ivCoinsPerSecond.setSmooth(true);
        ivCoinsPerSecond.setCache(true);


        textClicks = new Text(String.valueOf(AMOUNT_OF_COINS));
        textClicks.setTranslateX(iv2.getTranslateX());
        textClicks.setTranslateY(iv2.getTranslateY());
        textClicks.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.2 * iv2.getFitWidth())));

        textCoinsPerSecond = new Text(String.valueOf(COINS_PER_SECOND) + " / s");
        textCoinsPerSecond.setTranslateX(ivCoinsPerSecond.getTranslateX());
        textCoinsPerSecond.setTranslateY(ivCoinsPerSecond.getTranslateY());
        textCoinsPerSecond.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.2 * ivCoinsPerSecond.getFitWidth())));

        imageCoin = new Image("images/coin.png");
        ivCoin = new ImageView();
        ivCoin.setImage(imageCoin);
        ivCoin.setTranslateY(textClicks.getTranslateY());
        ivCoin.setTranslateX(textClicks.getTranslateX()-70);
        ivCoin.setFitWidth(25);
        ivCoin.setPreserveRatio(true);
        ivCoin.setSmooth(true);
        ivCoin.setCache(true);

        Button buttonShop = new Button();
        buttonShop.setText("Shop");
        buttonShop.setId("shiny-orange");
        buttonShop.setTranslateX(ivCoinsPerSecond.getTranslateX());
        buttonShop.setTranslateY(ivCoinsPerSecond.getTranslateY()+buttonShop.getHeight()+70);


        Button buttonOptions = new Button();
        buttonOptions.setText("Options");
        buttonOptions.setId(buttonShop.getId());
        buttonOptions.setTranslateX(buttonShop.getTranslateX());
        buttonOptions.setTranslateY(buttonShop.getTranslateY()+50);

        ImageView ivFarmBackground = new ImageView(imageBackgroundClicks);
        ivFarmBackground.setTranslateY(BUILDINGS_WINDOW_TRANSLATE_Y);
        ivFarmBackground.setTranslateX(BUILDINGS_WINDOW_TRANSLATE_X);
        ivFarmBackground.setFitWidth(250);
        ivFarmBackground.setFitHeight(75);
        ivFarmBackground.toBack();
        ivFarmBackground.setSmooth(true);
        ivFarmBackground.setCache(true);

        ivFarmBackground.setOnMouseClicked((MouseEvent e) ->{
            buy(Farm);
        });



        ImageView ivFarm = new ImageView(new Image("images/farm.png"));
        ivFarm.setTranslateX(ivFarmBackground.getTranslateX()-75);
        ivFarm.setTranslateY(ivFarmBackground.getTranslateY()+3);
        ivFarm.toFront();
        ivFarm.setFitWidth(ivFarmBackground.getFitWidth()/3);
        ivFarm.setPreserveRatio(true);
        ivFarm.setSmooth(true);
        ivFarm.setCache(true);
        ivFarm.setOnMouseClicked((MouseEvent e) ->{
            buy(Farm);
        });

        Text farmText = new Text(Farm.getName());
        farmText.setTranslateY(ivFarm.getTranslateY()-28);
        farmText.setTranslateX(ivFarm.getTranslateX()+80);
        farmText.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.09 * ivFarmBackground.getFitWidth())));
        farmText.setOnMouseClicked((MouseEvent e) ->{
            buy(Farm);
        });


        farmCost = new Text(String.valueOf(Farm.getCost()));
        farmCost.setTranslateY(farmText.getTranslateY()+25);
        farmCost.setTranslateX(farmText.getTranslateX());
        farmCost.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.09 * ivFarmBackground.getFitWidth())));
        farmCost.setOnMouseClicked((MouseEvent e) ->{
            buy(Farm);
        });

        farmAmount = new Text(String.valueOf(Farm.getAmount()));
        farmAmount.setTranslateY(ivFarm.getTranslateY()-5);
        farmAmount.setTranslateX(ivFarm.getTranslateX()+160);
        farmAmount.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.15 * ivFarmBackground.getFitWidth())));
        farmAmount.setOnMouseClicked((MouseEvent e) ->{
            buy(Farm);
        });

        farmProportion = new Text(String.valueOf((int)Farm.getProportion(COINS_PER_SECOND)) + " %");
        farmProportion.setTranslateX(farmCost.getTranslateX());
        farmProportion.setTranslateY(farmCost.getTranslateY()+25);
        farmProportion.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.06 * ivFarmBackground.getFitWidth())));
        farmProportion.setOnMouseClicked((MouseEvent e) ->{
            buy(Farm);
        });


        ImageView ivWindmillBackground = new ImageView(imageBackgroundClicks);
        ivWindmillBackground.setTranslateY(ivFarmBackground.getTranslateY()+ivFarmBackground.getFitHeight());
        ivWindmillBackground.setTranslateX(ivFarmBackground.getTranslateX());
        ivWindmillBackground.setFitWidth(ivFarmBackground.getFitWidth());
        ivWindmillBackground.setFitHeight(ivFarmBackground.getFitHeight());
        ivWindmillBackground.setSmooth(true);
        ivWindmillBackground.setCache(true);
        ivWindmillBackground.setOnMouseClicked((MouseEvent e) ->{
            buy(Windmill);
        });

        ImageView ivWindmill = new ImageView(new Image(Windmill.getUrl()));
        ivWindmill.setTranslateX(ivWindmillBackground.getTranslateX()-75);
        ivWindmill.setTranslateY(ivWindmillBackground.getTranslateY()+2);
        ivWindmill.setFitWidth(ivWindmillBackground.getFitWidth()/3);
        ivWindmill.setFitHeight(ivWindmillBackground.getFitHeight()-7);
        ivWindmill.setCache(true);
        ivWindmill.setSmooth(true);
        ivWindmill.setOnMouseClicked((MouseEvent e) ->{
            buy(Windmill);
        });

        Text windmillText = new Text(Windmill.getName());
        windmillText.setTranslateY(ivWindmill.getTranslateY()-28);
        windmillText.setTranslateX(ivWindmill.getTranslateX()+80);
        windmillText.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.09 * ivWindmillBackground.getFitWidth())));
        windmillText.setOnMouseClicked((MouseEvent e) ->{
            buy(Windmill);
        });

        windmillCost = new Text(String.valueOf(Windmill.getCost()));
        windmillCost.setTranslateY(windmillText.getTranslateY()+25);
        windmillCost.setTranslateX(windmillText.getTranslateX());
        windmillCost.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.09 * ivWindmillBackground.getFitWidth())));
        windmillCost.setOnMouseClicked((MouseEvent e) ->{
            buy(Windmill);
        });

        windmillAmount = new Text(String.valueOf(Windmill.getAmount()));
        windmillAmount.setTranslateY(ivWindmill.getTranslateY()-5);
        windmillAmount.setTranslateX(ivWindmill.getTranslateX()+160);
        windmillAmount.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.15 * ivWindmillBackground.getFitWidth())));
        windmillAmount.setOnMouseClicked((MouseEvent e) ->{
            buy(Windmill);
        });

        windmillProportion = new Text(String.valueOf((int)Windmill.getProportion(COINS_PER_SECOND)) + " %");
        windmillProportion.setTranslateX(windmillCost.getTranslateX());
        windmillProportion.setTranslateY(windmillCost.getTranslateY()+25);
        windmillProportion.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.06 * ivWindmillBackground.getFitWidth())));
        windmillProportion.setOnMouseClicked((MouseEvent e) ->{
            buy(Windmill);
        });



        root.getChildren().addAll(iv2, textClicks, ivCoin, ivFarmBackground, ivFarm, buttonShop, buttonOptions, ivCoinsPerSecond, textCoinsPerSecond, farmCost, farmText, farmAmount, farmProportion, ivWindmillBackground, ivWindmill, windmillAmount, windmillCost, windmillText, windmillProportion,textClick);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    void buy(Building b){
        if(b.getCost() > AMOUNT_OF_COINS) return;
        AMOUNT_OF_COINS-= b.getCost();
        b.setAmount(b.getAmount()+1);
        b.setProduction();
        COINS_PER_SECOND+=b.getProduction();
    }

    void click(){
        AMOUNT_OF_COINS+=GAIN_PER_CLICK;
    }

    void clickPerSecond(){
        AMOUNT_OF_COINS+=(COINS_PER_SECOND*0.5);
    }


    void refreshGUI(){
        textClicks.setText(format(AMOUNT_OF_COINS));
        textCoinsPerSecond.setText(format(COINS_PER_SECOND) + " /s");
        farmCost.setText(format((long)Farm.getCost()));
        farmAmount.setText(format((long) Farm.getAmount()));
        farmProportion.setText(String.valueOf((int)Farm.getProportion(COINS_PER_SECOND)) + " %");
        windmillCost.setText(format((long) Windmill.getCost()));
        windmillAmount.setText(format((long) Windmill.getAmount()));
        windmillProportion.setText(String.valueOf((int) Windmill.getProportion(COINS_PER_SECOND)) + " %");
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "B");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "Qa");
        suffixes.put(1_000_000_000_000_000_000L, "Qi");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    Image crop(Image image, int x, int y, int width, int height){
        PixelReader reader = image.getPixelReader();
        WritableImage newImage = new WritableImage(reader, x, y, width, height);
        return newImage;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
