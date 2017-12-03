package Windows;

import Achievments.Achievement;
import Buildings.Farm;
import Buildings.Inn;
import Buildings.Windmill;
import Player.Player;
import Tabs.DrawAchievementTab;
import Tabs.DrawUpgradeTab;
import Upgrades.Upgrade;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import uiContainers.DrawMaster;

import java.util.*;

import uiContainers.DrawPanel;

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
			if(e.getPickResult().getIntersectedNode() == null){
				return;
			}
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
		for (int i = 0; i < 10; i++) {
			RowConstraints row = new RowConstraints(100);
			gpPanels.getRowConstraints().add(row);
		}

		gpPanels.setVgap(5);
		gpPanels.setPadding(new Insets(5, 0, 0, 0));

		ArrayList<Upgrade> list = new ArrayList<>();
		ArrayList<Achievement> list2 = new ArrayList<>();

		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));
		list2.add(new Achievement("kek", "kek", false, false, new ImageView(new Image("images/coin.png"))));


		DrawUpgradeTab dut = new DrawUpgradeTab(list);
		DrawAchievementTab dat = new DrawAchievementTab(list2);

		DrawPanel dpUpgrades = new DrawPanel("images/upgrade.png", "Upgrades", dut);
		DrawPanel dpAchievements = new DrawPanel("images/trophy.png", "Achievements", dat);

		dpUpgrades.setPrefWidth(100);
		gpPanels.setMargin(dpUpgrades, new Insets(10));
		gpPanels.setMargin(dpAchievements, new Insets(10));
		gpPanels.add(dpUpgrades, 0,0);
		gpPanels.add(dpAchievements, 0,1);


		GridPane gpBuildings = new GridPane();
		for (int i = 0; i < 10; i++) {
			RowConstraints row = new RowConstraints(100);
			gpBuildings.getRowConstraints().add(row);
		}

		uiContainers.DrawStats myStatsObject = new uiContainers.DrawStats(Player.getPlayer());
		hbStats.getChildren().add(myStatsObject);

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
		root.setCenter(dut);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
			DrawMaster.getDrawMaster().fixSizes();
		});

		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
			DrawMaster.getDrawMaster().fixSizes();
		});
	}

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
