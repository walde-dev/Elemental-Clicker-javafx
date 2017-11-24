package JavaFX_Stages;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uiContainers.DrawMaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

import Buildings.Building;

public class Main extends Application {

	public final int GAME_WIDTH = 1200;
	public final int GAME_HEIGHT = 800;

	public final double CLICK_WINDOW_TRANSLATE_X = -GAME_WIDTH / 2.4;
	public final double CLICK_WINDOW_TRANSLATE_Y = -GAME_HEIGHT / 2.18;

	public final double BUILDINGS_WINDOW_TRANSLATE_X = GAME_WIDTH / 2.5;
	public final double BUILDINGS_WINDOW_TRANSLATE_Y = -GAME_HEIGHT / 2.18;

	public long AMOUNT_OF_COINS = 100000000;
	public double COINS_PER_SECOND = 0;
	public long GAIN_PER_CLICK = 1;

	Text textClicks, textCoinsPerSecond, farmCost, farmAmount, windmillCost, windmillAmount, farmProportion,
			windmillProportion, innCost, innAmount, innProportion;
	ImageView ivCoin;
	Image imageCoin;

	Building Farm = Buildings.Farm.getFarm();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Elemental Clicker");
		primaryStage.setResizable(true);
		primaryStage.setOnCloseRequest(ae -> {
			System.exit(0);
		});


		BorderPane root = new BorderPane();
		root.setId("background-neutral");
		root.setOnMouseClicked((MouseEvent e) -> {
			click();
		});

		Scene mainScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
		mainScene.getStylesheets().add(Main.class.getResource("/images/styles.css").toExternalForm());

		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				uiContainers.DrawMaster.getDrawMaster().refreshTheItems();
			}
		}, 900, 1);

		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				clickPerSecond();
			}
		}, 1000, 500);

		Font alagard = Font.loadFont(getClass().getResourceAsStream("/Font/Alagard.ttf"), 20);

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
		ivCoinsPerSecond.setTranslateY(iv2.getTranslateY() + ivCoinsPerSecond.getFitHeight() + 20);
		ivCoinsPerSecond.setSmooth(true);
		ivCoinsPerSecond.setCache(true);

		textClicks = new Text(String.valueOf(AMOUNT_OF_COINS));
		textClicks.setTranslateX(iv2.getTranslateX());
		textClicks.setTranslateY(iv2.getTranslateY());
		textClicks.setStyle(String.format("-fx-font-size: %dpx;", (int) (0.2 * iv2.getFitWidth())));

		textCoinsPerSecond = new Text(String.valueOf(COINS_PER_SECOND) + " / s");
		textCoinsPerSecond.setTranslateX(ivCoinsPerSecond.getTranslateX());
		textCoinsPerSecond.setTranslateY(ivCoinsPerSecond.getTranslateY());
		textCoinsPerSecond
				.setStyle(String.format("-fx-font-size: %dpx;", (int) (0.2 * ivCoinsPerSecond.getFitWidth())));

		imageCoin = new Image("images/coin.png");
		ivCoin = new ImageView();
		ivCoin.setImage(imageCoin);
		ivCoin.setTranslateY(textClicks.getTranslateY());
		ivCoin.setTranslateX(textClicks.getTranslateX() - 70);
		ivCoin.setFitWidth(25);
		ivCoin.setPreserveRatio(true);
		ivCoin.setSmooth(true);
		ivCoin.setCache(true);

		GridPane gp = new GridPane();
		for (int i = 0; i < 10; i++) {
			RowConstraints row = new RowConstraints(100);
			gp.getRowConstraints().add(row);
		}

		uiContainers.DrawBuilding myFarmViewObject = new uiContainers.DrawBuilding(Buildings.Farm.getFarm());
		gp.add(myFarmViewObject, 0, 0);
		myFarmViewObject.setOnMouseClicked((MouseEvent e) -> {
			buy(Buildings.Farm.getFarm());
		});
		
		uiContainers.DrawBuilding myWindmillObject = new uiContainers.DrawBuilding(Buildings.Windmill.getWindmill());
		gp.add(myWindmillObject, 0, 1);
		myWindmillObject.setOnMouseClicked((MouseEvent e) -> {
			buy(Buildings.Windmill.getWindmill());
		});
		

		gp.setMaxHeight(GAME_HEIGHT);
		gp.setPrefHeight(GAME_HEIGHT);

		root.setRight(gp);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
			DrawMaster.getDrawMaster().fixSizes();
		});

		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
			DrawMaster.getDrawMaster().fixSizes();
		});
	}

	void buy(Building b) {
		if (b.getCost() > AMOUNT_OF_COINS)
			return;
		AMOUNT_OF_COINS -= b.getCost();
		COINS_PER_SECOND -= b.getTotalProduction();
		b.setAmount(b.getAmount() + 1);
		COINS_PER_SECOND += b.getTotalProduction();
	}

	void click() {
		AMOUNT_OF_COINS += GAIN_PER_CLICK;
	}

	void clickPerSecond() {
		AMOUNT_OF_COINS += (COINS_PER_SECOND * 0.5);
	}

	void refreshGUI() {

	}

	Image crop(Image image, int x, int y, int width, int height) {
		PixelReader reader = image.getPixelReader();
		WritableImage newImage = new WritableImage(reader, x, y, width, height);
		return newImage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
