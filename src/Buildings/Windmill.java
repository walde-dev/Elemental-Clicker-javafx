package Buildings;

import javafx.scene.image.Image;

public class Windmill extends Building {

	private static Windmill myWindmill;

	private Windmill() {
		super("Windmill", 150, 6, new Image("images/windmill.png"));
	}

	public static Windmill getWindmill() {
		if (myWindmill == null) {
			myWindmill = new Windmill();
		}
		return myWindmill;
	}

}
