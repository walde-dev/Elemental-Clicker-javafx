package Buildings;

import javafx.scene.image.Image;
import uiContainers.Drawable;

public class Farm extends Building {

	private static Farm myFarm;
	
	private Farm() {
		super("Farm", 10, 2, new Image("images/farm.png"));
	}

	public static Farm getFarm() {
		if (myFarm == null) {
			myFarm = new Farm();
		}
		return myFarm;
	}

}
