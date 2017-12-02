package Windows;

import Buildings.Farm;
import Buildings.Inn;
import Buildings.Windmill;
import Player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import uiContainers.DrawMaster;

import java.util.*;

import Buildings.Building;

import javax.swing.text.Position;

public class Main extends Application {

	public final int GAME_WIDTH = 1200;
	public final int GAME_HEIGHT = 800;


	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Elemental Clicker");
		primaryStage.setResizable(true);
		primaryStage.setOnCloseRequest(ae -> {
			System.exit(0);
		});


		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: #1b55a6");
		root.setOnMouseClicked((MouseEvent e) -> {
			click();
		});

		Scene mainScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
		mainScene.getStylesheets().add(Main.class.getResource("/images/styles.css").toExternalForm());

		Timeline bThread = new Timeline(new
				KeyFrame(Duration.millis(5), ae -> {
				uiContainers.DrawMaster.getDrawMaster().refreshTheItems();
		}));
		bThread.setCycleCount(Animation.INDEFINITE);
		bThread.play();

		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				clickPerSecond();
			}
		}, 1000, 500);


		HBox hbStats = new HBox();
		hbStats.setSpacing(100);
		hbStats.setStyle("-");

		GridPane gpPanels = new GridPane();

		GridPane gpBuildings = new GridPane();
		for (int i = 0; i < 10; i++) {
			RowConstraints row = new RowConstraints(100);
			gpBuildings.getRowConstraints().add(row);
		}

		uiContainers.DrawStats myStatsObject = new uiContainers.DrawStats(Player.getPlayer());
		hbStats.getChildren().add(myStatsObject);

		/*uiContainers.DrawBuilding myFarmViewObject = new uiContainers.DrawBuilding(Buildings.Farm.getFarm());
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
		});*/

		Farm farm = Buildings.Farm.getFarm();
		gpBuildings.add(farm.getView(),0,0);

		Windmill windmill = Buildings.Windmill.getWindmill();
		gpBuildings.add(windmill.getView(), 0,1);

		Inn inn = Buildings.Inn.getInn();
		gpBuildings.add(inn.getView(), 0,2);

		gpBuildings.setMaxHeight(GAME_HEIGHT);
		gpBuildings.setPrefHeight(GAME_HEIGHT);

		root.setRight(gpBuildings);
		root.setTop(hbStats);
		root.setLeft(gpPanels);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
			DrawMaster.getDrawMaster().fixSizes();
		});

		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
			DrawMaster.getDrawMaster().fixSizes();
		});
	}

	/*void buy(Building b) {
		if (b.getCost() > p.getCoins())
			return;
		p.setCoins(p.getCoins() - b.getCost());
		p.setCoinsPerSecond(p.getCoinsPerSecond() - b.getTotalProduction());
		b.setAmount(b.getAmount() + 1);
		p.setCoinsPerSecond(p.getCoinsPerSecond() + b.getTotalProduction());
	}*/

	void click() {
		Player.getPlayer().setCoins(Player.getPlayer().getCoins() + Player.getPlayer().getCoinsPerClick());
	}

	void clickPerSecond() {
		Player.getPlayer().setCoins(Player.getPlayer().getCoins() + Player.getPlayer().getCoinsPerSecond() * 0.5);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
