package Windows;

import Player.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uiContainers.DrawMaster;

import java.util.*;

import Buildings.Building;

public class Main extends Application {

	public final int GAME_WIDTH = 1200;
	public final int GAME_HEIGHT = 800;

	public final double CLICK_WINDOW_TRANSLATE_X = -GAME_WIDTH / 2.4;
	public final double CLICK_WINDOW_TRANSLATE_Y = -GAME_HEIGHT / 2.18;

	public final double BUILDINGS_WINDOW_TRANSLATE_X = GAME_WIDTH / 2.5;
	public final double BUILDINGS_WINDOW_TRANSLATE_Y = -GAME_HEIGHT / 2.18;

	Player p = new Player(0, 0, 0, 0,0);

	
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


		HBox hb = new HBox();
		hb.setSpacing(100);
		hb.setPadding(new Insets(15,12,15,12));
		hb.setStyle("-");

		GridPane gp = new GridPane();
		for (int i = 0; i < 10; i++) {
			RowConstraints row = new RowConstraints(100);
			gp.getRowConstraints().add(row);
		}

		uiContainers.DrawStats myStatsObject = new uiContainers.DrawStats(p);
		hb.getChildren().add(myStatsObject);

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

		uiContainers.DrawBuilding myInnObject = new uiContainers.DrawBuilding(Buildings.Inn.getInn());
		gp.add(myInnObject, 0, 2);
		myInnObject.setOnMouseClicked((MouseEvent e) -> {
			buy(Buildings.Inn.getInn());
		});
		

		gp.setMaxHeight(GAME_HEIGHT);
		gp.setPrefHeight(GAME_HEIGHT);

		root.setRight(gp);
		root.setTop(hb);
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
		if (b.getCost() > p.getCoins())
			return;
		p.setCoins(p.getCoins() - b.getCost());
		p.setCoinsPerSecond(p.getCoinsPerSecond() - b.getTotalProduction());
		b.setAmount(b.getAmount() + 1);
		p.setCoinsPerSecond(p.getCoinsPerSecond() + b.getTotalProduction());
	}

	void click() {
		p.setCoins(p.getCoins() + p.getCoinsPerClick());
	}

	void clickPerSecond() {
		p.setCoins(p.getCoins() + p.getCoinsPerSecond() * 0.5);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
