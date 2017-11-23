package Buildings;

public class Farm extends Building {

	private static Farm myFarm;

	private Farm() {
		super("Farm", 10, 2);
	}

	public static Farm getFarm() {
		if (myFarm == null) {
			myFarm = new Farm();
		}
		return myFarm;
	}

}
