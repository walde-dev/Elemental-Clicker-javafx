package Buildings;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductionHandler {
	private static ProductionHandler ph;
	private ArrayList<Building> productionBuildings;

	private ProductionHandler() {
		productionBuildings = new ArrayList<Building>();
	}

	public static ProductionHandler getProductionHandler() {
		if (ph == null) {
			ph = new ProductionHandler();
		}
		return ph;
	}

	public void addBuilding(Building b) {
		productionBuildings.add(b);
	}
	
	public double getTotalProduction() {
		return productionBuildings.stream().map(b -> b.getTotalProduction()).collect(Collectors.summarizingDouble(Double::doubleValue)).getSum();
	}
}
