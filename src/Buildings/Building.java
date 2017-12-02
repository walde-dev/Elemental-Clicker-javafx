package Buildings;

import Player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import uiContainers.DrawBuilding;
import uiContainers.Drawable;

public class Building {
	private String name;
	private String url;
	private double cost;
	private double coinsPerSecond;
	private int amount;
	private Player p;
	private Image icon;
	private DrawBuilding view;

	public Image getIcon() {
		return icon;
	}

	protected Building(String name, int cost, int coinsPerSecond, Image icon) {
		this.name = name;
		this.cost = cost;
		this.coinsPerSecond = coinsPerSecond;
		this.amount = 0;
		this.p = Player.getPlayer();
		this.icon = icon;
		ProductionHandler.getProductionHandler().addBuilding(this);
		view = new uiContainers.DrawBuilding(this);
		view.setOnMouseClicked((MouseEvent e) -> {
			buy();
		});
	}

	public HBox getView() {
		return view;
	}

	void buy() {
		if (getCost() > p.getCoins()){
			view.setErrorTimeLeft(100);
			return;
		}
		System.out.println(getCost() + " " + p.getCoins());
		p.reduceCoins(getCost());
		p.setCoinsPerSecond(p.getCoinsPerSecond() - this.getTotalProduction());
		setAmount(amount + 1);
		p.setCoinsPerSecond(p.getCoinsPerSecond() + this.getTotalProduction());
	}

	public double getProportion() {
		double p = ProductionHandler.getProductionHandler().getTotalProduction();
		if (p == 0)
			return 0;
		return 100 * (getTotalProduction() / p);
	}

	public double getTotalProduction() {
		if (amount == 0)
			return 0;
		if (amount == 1)
			return coinsPerSecond;
		return -23.0 / 3.0 * Math.pow(20, -amount) * (Math.pow(20, amount) - Math.pow(23, amount)) * coinsPerSecond;

	}

	public String getName() {
		return name;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public double getCost() {
		return (cost * Math.pow(1.15, amount));
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public double getCoinsPerSecond() {
		return coinsPerSecond;
	}

	public void draw() {

	}

}
