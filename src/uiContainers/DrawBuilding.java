package uiContainers;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import Buildings.Building;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DrawBuilding extends HBox implements Drawable {
	private Text buildingText;
	private Text buildingCost;
	private Text buildingProportion;
	private Text buildingAmount;
	private Building b;

	public DrawBuilding(Building b) {
		this.b = b;
		uiContainers.DrawMaster.getDrawMaster().add(this);

		ImageView ivbuilding = new ImageView(b.getIcon());
		ivbuilding.setFitWidth(80);
		ivbuilding.setFitHeight(80);
		ivbuilding.setPreserveRatio(true);
		ivbuilding.setSmooth(true);
		ivbuilding.setCache(true);

		VBox vb = new VBox();
		vb.setStyle("-fx-border-color: red;");
		vb.setAlignment(Pos.CENTER);
		buildingText = new Text(b.getName());
		buildingText.setStyle("-fx-font-size: 12px;");

		buildingCost = new Text(String.format("%.0f", b.getCost()));
		buildingCost.setStyle("-fx-font-size: 12px;");

		buildingProportion = new Text(b.getProportion() == 0.0 ? String.format("%.0f%s", b.getProportion(), "%")
				: String.format("%.1f%s", b.getProportion(), "%"));
		buildingProportion.setStyle("-fx-font-size: 12px;");

		vb.getChildren().addAll(buildingText, buildingCost, buildingProportion);

		buildingAmount = new Text(String.valueOf(b.getAmount()));
		buildingAmount.setStyle("-fx-font-size: 30px;");
		buildingAmount.textProperty().addListener((obs, oldVal, newVal) -> {
			DrawMaster.getDrawMaster().fixSizes();
		});

		setAlignment(Pos.CENTER);
		setSpacing(25);
		setPadding(new Insets(2));
		setStyle(
				"-fx-border-color: black;-fx-background-image: url('images/background_clicks.png');-fx-background-size: 201 100;");
		getChildren().addAll(ivbuilding, vb, buildingAmount);
	}

	@Override
	public void fixSize() {
		setStyle(String.format("-fx-border-color: black;" + "-fx-background-image: url('images/background_clicks.png');"
				+ "-fx-background-size: %.0f %.0f;", getWidth(), getHeight()));
	}

	@Override
	public void draw() {
		buildingText.setText(b.getName());
		buildingCost.setText(formartiermich(b.getCost()));
		buildingProportion.setText(b.getProportion() == 0.0 ? String.format("%.0f%s", b.getProportion(), "%")
				: String.format("%.1f%s", b.getProportion(), "%"));
		buildingAmount.setText(String.valueOf(b.getAmount()));
	}

	private static final NavigableMap<Double, String> suffixes = new TreeMap<>();
	static {
		suffixes.put(1_000D, "k");
		suffixes.put(1_000_000D, "M");
		suffixes.put(1_000_000_000D, "B");
		suffixes.put(1_000_000_000_000D, "T");
		suffixes.put(1_000_000_000_000_000D, "Qa");
		suffixes.put(1_000_000_000_000_000_000D, "Qi");
		suffixes.put(1_000_000_000_000_000_000_000D, "Sx");
		suffixes.put(1_000_000_000_000_000_000_000_000D, "Sp");
		suffixes.put(1_000_000_000_000_000_000_000_000_000D, "Oc");
		suffixes.put(1_000_000_000_000_000_000_000_000_000_000D, "No");
		suffixes.put(1_000_000_000_000_000_000_000_000_000_000_000D, "Dc");
		suffixes.put(1_000_000_000_000_000_000_000_000_000_000_000_000D, "Ud");
	}

	public static String formartiermich(double value) {
		if (value == Double.MIN_VALUE)
			return formartiermich(Double.MIN_VALUE + 1);
		if (value < 0)
			return "-" + formartiermich(-value);
		if (value < 1000)
			return String.format("%.0f", value);

		Map.Entry<Double, String> e = suffixes.floorEntry(value);
		Double divideBy = e.getKey();
		String suffix = e.getValue();

		double truncated = value / (divideBy / 10);
		boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
		return (hasDecimal ? String.format("%.2f", (truncated / 10d)) + suffix
				: String.format("%.2f", (truncated / 10)) + suffix).replace(",", ".");
	}

}
