package platform.game.level;

import platform.game.Block;
import platform.game.LevelBlock;
import platform.game.Breakable;
import platform.game.Color;
import platform.game.Door;
import platform.game.Exit;
import platform.game.Heart;
import platform.game.Jumper;
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
	    
	    world.register(new LevelBlock(1, new Box(new Vector(-6.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level1()));
	    world.register(new LevelBlock(2, new Box(new Vector(-3.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new BasicInteract()));
	    world.register(new LevelBlock(3, new Box(new Vector(0.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new BasicLevel()));
	    world.register(new LevelBlock(4, new Box(new Vector(3.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level1()));
	    world.register(new LevelBlock(10, new Box(new Vector(6.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level1()));
        
	}
}
