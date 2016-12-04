package platform.game;

import platform.game.SimpleSprite;
import platform.util.Vector;

public class Fire extends SimpleSprite {
	
	public Fire(Vector nPos,double nCoold, double nSize) {
		super("fireball", nPos, nCoold, nSize, true);
	}
	
}
