package platform.game.level;

import com.sun.glass.events.KeyEvent;

import platform.game.Actor;
import platform.game.World;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
import platform.game.Command;
import platform.game.Limits;
import platform.game.SimpleSprite;

/**
 * Base class for level factories, which provides fade in transition. Subclasses
 * are requires to override <code>register</code>.
 */
public abstract class Level extends Actor {

    private double fadein;

    public Level() {
        fadein = 1.1;
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void update(Input input) {
        fadein -= input.getDeltaTime();
        if (fadein <= 0.0)
            getWorld().unregister(this);
    }

    @Override
    public void draw(Input input, Output output) {
        Sprite sprite = getSprite("pixel.black");
        output.drawSprite(sprite, output.getBox(), 0.0, fadein);
    }
    
    /** @return a new instance of default level */
    public static Level createDefaultLevel() {
        return new Selection();
    }
    
    public void niveauDeJeu(World nWorld) {
    	nWorld.register(new Command(nWorld));
        nWorld.register(new SimpleSprite("background2", new Vector(0.0, 0.0), 100, false));	
        nWorld.register(new Limits(new Box(new Vector(0.0,0.0), 50, 50)));
    }
}
