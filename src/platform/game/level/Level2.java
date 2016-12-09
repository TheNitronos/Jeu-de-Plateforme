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
import platform.game.Slime;
import platform.game.Signal;
import platform.game.Lever;

public class Level2 extends Level{
	@Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(new Level2());
        
        for (int i = -6 ; i <= 6 ; i += 2){
        	world.register(new Block (new Box(new Vector(i, 0), 2, 1), world.getLoader().getSprite("stone.2")));
        }
        world.register(new Block (new Box(new Vector(-7, 1), 2, 1), world.getLoader().getSprite("stone.2")));
        world.register(new Block (new Box(new Vector(7.5, 1), 3, 1), world.getLoader().getSprite("stone.3")));
        
        Slime slime = new Slime(new Vector(0, 1));
        world.register(slime);
        
        Torch torch1 = new Torch(new Vector(-3.5, 3), false);
        Torch torch2 = new Torch(new Vector(3.5, 3), false);
        world.register(torch1);
        world.register(torch2);
        
        Lever lever1 = new Lever(new Vector(8.5, 2), 7.0);
        world.register(lever1);
        
        Player player = new Player(new Vector(0.0, 0.0), new Vector(-6.5, 2.0));
        world.register(player);
        world.register(new Overlay(player));
        
        
        
        
        
	}
}
