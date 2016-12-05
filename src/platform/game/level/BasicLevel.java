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

public class BasicLevel extends Level {

    @Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(new BasicLevel());
        
        // Create blocks
        world.register(new Block(new Box(new Vector(0, 0), 8, 2), world.getLoader().getSprite("stone.broken.2")));
        world.register(new Spike(new Vector(0,1.25)));
        world.register(new Block(new Box(new Vector(-1.5, 1.5), 1, 1), world.getLoader().getSprite("stone.broken.1")));
        Player player = new Player(new Vector(0, 1), new Vector(1.5,4));
        
        world.register(player);
        world.register(new Jumper(new Vector(1.5, 1.5)));
        world.register(new Limits(new Box(Vector.ZERO, 40,30)));
        world.register(new Overlay(player));
        world.register(new Heart(new Vector(-2.0, 3.0)));
        world.register(new Torch(new Vector(-3.0, 4.0) , true));
        
        
    }
    
}
