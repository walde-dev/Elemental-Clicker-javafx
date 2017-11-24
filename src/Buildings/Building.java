package Buildings;

import javafx.scene.image.Image;

public class Building {
	private String name;
	private String url;
	private int cost;
	private double coinsPerSecond;
	private int amount;
	private Image icon;

	public Image getIcon() {
		return icon;
	}

	protected Building(String name, int cost, int coinsPerSecond, Image icon) {
		this.name = name;
		this.cost = cost;
		this.coinsPerSecond = coinsPerSecond;
		this.amount = 0;
		this.icon = icon;
		ProductionHandler.getProductionHandler().addBuilding(this);
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
