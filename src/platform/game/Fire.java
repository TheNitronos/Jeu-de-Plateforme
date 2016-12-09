package platform.game;

import platform.game.SimpleSprite;
import platform.util.Vector;

public class Fire extends SimpleSprite {
	public Fire(Vector nPos,double nCoold) {
		super("fireball", nPos, nCoold, 0.5, true);
	}	
	
	@Override
	public int getPriority(){
		return 1;
	}
}
