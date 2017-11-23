package uiContainers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Farm extends HBox implements Drawable{
	private Image imageBackgroundClicks = new Image("images/background_clicks.png");
	private Text farmText;
	private Text farmCost;
	private Text farmProportion;
	private Text farmAmount;
	
	public Farm() {
		uiContainers.DrawMaster.getDrawMaster().add(this);
		
		ImageView ivFarm = new ImageView(new Image("images/farm.png"));
		ivFarm.setFitWidth(80);
		ivFarm.setFitHeight(80);
		ivFarm.setPreserveRatio(true);
		ivFarm.setSmooth(true);
		ivFarm.setCache(true);
		
		VBox vb = new VBox();
		vb.setStyle("-fx-border-color: red;");
		vb.setAlignment(Pos.CENTER);
		farmText = new Text(Buildings.Farm.getFarm().getName());
		farmText.setStyle("-fx-font-size: 12px;");

		farmCost = new Text(String.format("%.2f", Buildings.Farm.getFarm().getCost()));
		farmCost.setStyle("-fx-font-size: 12px;");

		farmProportion = new Text(
				String.valueOf(/* (int) Farm.getProportion(0.0)) + " %" */"asdfasdf"));
		farmProportion.setStyle("-fx-font-size: 12px;");
		
		vb.getChildren().addAll(farmText, farmCost, farmProportion);
		
		farmAmount = new Text(String.valueOf(Buildings.Farm.getFarm().getAmount()));
		farmAmount.setStyle("-fx-font-size: 30px;");
		
		setAlignment(Pos.CENTER);
		setSpacing(25);
		setPadding(new Insets(2));
		setStyle("-fx-border-color: black;");
		getChildren().addAll(ivFarm, vb, farmAmount);
	}

	@Override
	public void draw() {
		farmText.setText(Buildings.Farm.getFarm().getName());
		farmCost.setText(String.format("%.2f", Buildings.Farm.getFarm().getCost()));
		farmProportion.setText("asdfasdf");
		farmAmount.setText(String.valueOf(Buildings.Farm.getFarm().getAmount()));
	}
	
}
