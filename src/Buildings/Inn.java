package Buildings;

import javafx.scene.image.Image;

public class Inn extends Building {

	private static Inn myInn;

	private Inn() {
		super("Inn", 600, 20, new Image("images/inn.png"));
	}

	public static Inn getInn() {
		if (myInn == null) {
			myInn = new Inn();
		}
		return myInn;
	}

}
