package platform.game;

import platform.game.SimpleSprite;
import platform.util.Vector;

public class Background extends SimpleSprite {
	public Background() {
		//le contrstructeur de SimpleSprite se charge de tout
		super("background2", new Vector(0.0, 0.0), 80, false, Integer.MIN_VALUE);
	}
}