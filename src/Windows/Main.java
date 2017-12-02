package Windows;

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

	Player p = new Player(1000000000, 0, 0, 0,0);

	
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


		HBox hb = new HBox();
		hb.setSpacing(100);
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
