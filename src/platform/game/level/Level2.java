package platform.game.level;

import platform.game.*;

import platform.util.Box;
import platform.util.Vector;


public class Level2 extends Level{
	@Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(new Level2());
        world.register(new Command(world));
        
        world.register(new Limits(new Box(new Vector(0.0 , 0.0), 50,50)));
        world.register(new SimpleSprite("background2", new Vector(0.0, 0.0), 100, false));
        
        for (int i = -6 ; i <= 8 ; i += 2){
        	world.register(new Block (new Box(new Vector(i, 0), 2, 1), world.getLoader().getSprite("stone.2")));
        }
        world.register(new Block (new Box(new Vector(-7, 1), 2, 1), world.getLoader().getSprite("stone.2")));
        world.register(new Block (new Box(new Vector(7.5, 1), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block (new Box(new Vector(-7.5, 0), 1, 1), world.getLoader().getSprite("stone.1")));
        
        world.register(new Block (new Box(new Vector(12.5, -1), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block (new Box(new Vector(15.5, -1), 3, 1), world.getLoader().getSprite("stone.3")));

        
        for(int i = 0; i <= 5 ; ++i){
        	world.register(new Spike(new Vector(11.5 + i, -0.25)));
        }
        
        Slime slime = new Slime(new Vector(0, 1));
        world.register(slime);
        
        Torch torch1 = new Torch(new Vector(-3.5, 4), false);
        Torch torch2 = new Torch(new Vector(3.5, 4), false);
        world.register(torch1);
        world.register(torch2);
        
        Lever lever1 = new Lever(new Vector(8.5, 2), 4.0);
        world.register(lever1);
        
        world.register(new Door(new Box(new Vector(8.0, 2.0), 2, 1), new And( new And(slime, torch1), torch2), world.getLoader().getSprite("stone.2")));
        
        Player player = new Player(new Vector(0.0, 0.0), new Vector(-6.5, 2.0));
        world.register(player);
        world.register(new Overlay(player));
        
        world.register(new Mover(new Box(new Vector(10.0, 1.0), 2, 1), world.getLoader().getSprite("stone.broken.2"), new Vector(10.0, -5), lever1));
        
        world.register(new Block (new Box(new Vector(8, -6), 2, 1), world.getLoader().getSprite("stone.2")));
        world.register(new Block (new Box(new Vector(6, -6), 2, 1), world.getLoader().getSprite("stone.2")));
        
        world.register(new Block (new Box(new Vector(4, -7), 2, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block (new Box(new Vector(-1, -7), 2, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new Block (new Box(new Vector(1.5 , -12), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Spike(new Vector(0.5, -11.25)));
        world.register(new Spike(new Vector(2.5, -11.25)));
        world.register(new Jumper(new Vector(1.5, -11)));
        
        Key key1 = new Key(new Vector(1.5, -9), Color.RED);
        world.register(key1);
        
        world.register(new Block (new Box(new Vector(-3.5 , -6), 3, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new Exit(new Vector(-4.5, -5), key1, new Selection()));
        world.register(new ExitIndic(new Vector(-3.5, -5)));
        
        world.register(new Block(new Box(new Vector(-5.5 , -3.5), 1, 6), world.getLoader().getSprite("stone.7")));
        world.register(new Heart(new Vector(6.5, 2)));
        world.register(new Heart(new Vector(7.5, -5)));

        
        
	}
}
