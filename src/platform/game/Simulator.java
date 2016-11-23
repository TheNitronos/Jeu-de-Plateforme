package platform.game;

import platform.game.level.Level;
import java.util.ArrayList;
import java.util.List;
import platform.util.Box;

import platform.util.Input;
import platform.util.Loader;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
import platform.util.View;

/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World {

    private Loader loader;
  
    /**
     * Create a new simulator.
     * @param loader associated loader, not null
     * @param args level arguments, not null
     */
	public Simulator(Loader loader, String[] args) {
        if (loader == null)
            throw new NullPointerException();
        this.loader = loader;
      
	}
	
    /**
     * Simulate a single step of the simulation.
     * @param input input object to use, not null
     * @param output output object to use, not null
     */
	public void update(Input input, Output output) {
        
	View view = new View(input, output);
	Vector center = new Vector(0.0, 0.0);
	double radius = 10.0;
	view.setTarget(center, radius);
	Sprite sprite = loader.getSprite("heart.full");
	Box zone = new Box(new Vector(0.0, 0.0), 2, 2);
	view.drawSprite(sprite, zone);
	}

    @Override
    public Loader getLoader() {
        return loader;
    }

    
}
