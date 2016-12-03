package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;

public class LevelBox extends Block {
	int level;
	
	public LevelBox(int nLevel, Box b, Sprite s) {
		super(b, s);
		level = nLevel;
	}
	
}
