package platform.game.level;

import platform.game.World;
import platform.game.Block;
import platform.util.Box;
import platform.util.Vector;
import platform.game.Player;
import platform.game.SimpleSprite;
import platform.game.Jumper;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Spike;
import platform.game.Lever;
import platform.game.Mover;
import platform.game.Exit;
import platform.game.ExitIndic;
import platform.game.Breakable;
import platform.game.Color;
import platform.game.Command;
import platform.game.Key;
import platform.game.Door;


public class Level1 extends Level {
	@Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(new Level1());
        world.register(new Command(world));
        world.register(new SimpleSprite("background2", new Vector(0.0, 0.0), 100, false));
        
        world.register(new Block(new Box(new Vector(-9.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-6.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-3.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(0.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(3.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(6.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new Block(new Box(new Vector(-9.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-6.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-3.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(0.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(3.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(6.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new ExitIndic(new Vector(-8.0, 3.0)));
        
        world.register(new Block(new Box(new Vector(9.0, -5.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Jumper(new Vector(9.0, -4.0)));
        world.register(new Spike(new Vector(8.0, -4.25)));
        world.register(new Spike(new Vector(10.0, -4.25)));
        
        world.register(new Breakable(new Box(new Vector(7.0, 1.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(6.0, 1.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(5.0, 1.0), 1, 1), world.getLoader().getSprite("box.double")));
      
        Lever lever1 = new Lever(new Vector (-5.0, 1.0), 15.0);
        world.register(lever1);
        world.register(new Mover(new Box(new Vector(-8.0, 1), 1, 1), world.getLoader().getSprite("stone.broken.1"), new Vector(3.0, 1.0), lever1));
        
        Lever lever2 = new Lever(new Vector (-1.0, 3.0), 5.0);
        world.register(lever2);
        world.register(new Exit(new Vector(-10.0, 3.0), lever2, new Selection()));
        
        Key key1 = new Key(new Vector(-9.0, 1), Color.GREEN);
        world.register(key1);
        
        world.register(new Door(new Box(new Vector(-1.0, 3.0), 1.0, 1.0), key1, world.getLoader().getSprite("lock.green")));
        
        Player player = new Player(new Vector(0, 5), new Vector(0.0, 1.0));
        world.register(player);
        world.register(new Overlay(player));
        
        world.register(new Limits(new Box(new Vector(0.0,0.0), 40, 40)));
	}
}