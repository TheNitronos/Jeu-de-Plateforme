/*
 *	Author:      Samuel Chassot
 *	Date:        29 nov. 2016
 */


package platform.game.level;

import platform.game.World;
import platform.game.Block;
import platform.util.Box;
import platform.util.Vector;
import platform.game.Player;
import platform.game.Jumper;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Heart;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.Color;
import platform.game.Key;
import platform.game.Door;
import platform.game.Lever;
import platform.game.Mover;
import platform.game.Exit;
import platform.game.Breakable;

public class BasicInteract extends Level {
	
	@Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(new BasicLevel());
        
        world.register(new Block(new Box(new Vector(6.0, 0.0), 16, 2), world.getLoader().getSprite("stone.broken.2")));
        world.register(new Block(new Box(new Vector(-3.0, 1.0), 2, 4), world.getLoader().getSprite("stone.broken.8")));
        Player player = new Player(new Vector(0, 1), new Vector(0.75, 1.5));
        world.register(player);
        world.register(new Overlay(player));
        world.register(new Torch(new Vector(2.0, 2.0), true));
        world.register(new Spike(new Vector(-0.5, 1.25)));
        world.register(new Jumper(new Vector(-1.5, 1.5)));
        world.register(new Heart(new Vector(-2.5, 3.5)));
        world.register(new Limits(new Box(new Vector(0.0,0.0), 40, 40)));
        Key blue = new Key(new Vector(-3, 4), Color.BLUE);
        world.register(blue);
        
        
        world.register(new Breakable(new Box(new Vector(3.0, 2.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Door(new Box(new Vector(4.0, 1.5) , 1.0, 1.0), blue, world.getLoader().getSprite("lock.blue")));
        Lever lever1 = new Lever(new Vector (8.0, 1.5), 4.0);
        world.register(lever1);
        world.register(new Mover(new Box(new Vector(8.0, 0.75), 4, 0.5), world.getLoader().getSprite("stone.2"), new Vector(8.0, 10.0), lever1));
        world.register(new Exit(new Vector(4.0, 1.5), lever1, new Selection()));
	}
}