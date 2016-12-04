package platform.game;

import platform.util.Vector;

public class Unicorn extends SimpleSprite {
	public Unicorn(String nSprite, Vector nPos) {
		super("duck", nPos, 1.5, false);
	}
	
	@Override
	public int getPriority() {
		return 41;
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
