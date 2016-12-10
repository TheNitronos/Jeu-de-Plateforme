package platform.game.level;

import platform.game.LevelBlock;
import platform.game.SimpleSprite;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class Selection extends Level {
	@Override
	public void register(World world) {
	    super.register(world);
	    
	    world.register(new SimpleSprite("background2", new Vector(0.0, 0.0), 80, false, Integer.MIN_VALUE));
	    
	    world.register(new LevelBlock(1, new Box(new Vector(-6.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level1()));
	    world.register(new LevelBlock(2, new Box(new Vector(-3.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level2()));
	    world.register(new LevelBlock(3, new Box(new Vector(0.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level3()));
	    world.register(new LevelBlock(4, new Box(new Vector(3.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level4()));
	    world.register(new LevelBlock(5, new Box(new Vector(6.0, 0.0), 2, 2), world.getLoader().getSprite("box.empty"), new Level5()));   
	}
}
