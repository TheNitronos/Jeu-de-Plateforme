package platform.game.level;

import platform.game.Block;
import platform.game.Breakable;
import platform.game.Color;
import platform.game.Door;
import platform.game.Exit;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.LevelBox;
import platform.game.Key;
import platform.game.Lever;
import platform.game.Limits;
import platform.game.Mover;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class Selection extends Level {

	@Override
	public void register(World world) {
	    super.register(world);
	    	
	    world.register(new Block(new Box(new Vector(0.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty")));
        
	    
	}
}
