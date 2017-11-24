package uiContainers;

import java.util.ArrayList;

public class DrawMaster {
	private static DrawMaster dm;
	private ArrayList<Drawable> refreshItems;

	private DrawMaster() {
		refreshItems = new ArrayList<Drawable>();
	}

	public static DrawMaster getDrawMaster() {
		if (dm == null) {
			dm = new DrawMaster();
		}
		return dm;
	}

	public void add(Drawable d) {
		refreshItems.add(d);
	}

	public void refreshTheItems() {
		for(Drawable d : refreshItems)
			d.draw();
	}
	
	public void fixSizes() {
		for(Drawable d : refreshItems)
			d.fixSize();
	}
	
}
