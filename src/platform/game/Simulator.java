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
import platform.util.SortedCollection;

/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World {

    private Loader loader;
    private Vector currentCenter;
    private double currentRadius;
    private Vector expectedCenter;
    private double expectedRadius;
    private SortedCollection<Actor> actors;
    private ArrayList<Actor> registered;
    private ArrayList<Actor> unregistered;
  
    /**
     * Create a new simulator.
     * @param loader associated loader, not null
     * @param args level arguments, not null
     */
	public Simulator(Loader loader, String[] args) {
        if (loader == null)
            throw new NullPointerException();
        this.loader = loader;
        
        currentCenter = Vector.ZERO;
        currentRadius = 10.0;
        
        expectedCenter = Vector.ZERO;
        expectedRadius = 10.0;
        
        actors = new SortedCollection<Actor>();
        registered = new ArrayList<Actor>();
        unregistered = new ArrayList<Actor>();

        
      
	}
	@Override
	public void setView(Vector center, double radius){
		if (center == null){
			throw new NullPointerException();
		}
		if (radius <= 0.0){
			throw new IllegalArgumentException("radius must be positive");
		}
		expectedCenter = center;
		expectedRadius = radius;
	
	}
	
    /**
     * Simulate a single step of the simulation.
     * @param input input object to use, not null
     * @param output output object to use, not null
     */
	public void update(Input input, Output output) {
		
	double factor = 0.001;
	
	
	currentCenter = currentCenter.mul(1.0 - factor).add(expectedCenter.mul(factor));
	currentRadius = currentRadius * (1.0 - factor) + expectedRadius*factor;
	
        
	View view = new View(input, output);
	view.setTarget(currentCenter, currentRadius);
	
//	Sprite sprite = loader.getSprite("heart.full");
//	Box zone = new Box(new Vector(0.0, 0.0), 2, 2);
	
//	view.drawSprite(sprite, zone);
	
	if (view.getMouseButton(1).isPressed()){
		setView(view.getMouseLocation(), 10.0);
	}
	
	//apply update
	for (Actor a : actors){
		a.update(view);
	}
	
	//Draw everything
	for (Actor a : actors.descending()){
		a.draw(view, view);
	}
	
	//add registered actors
	for (int i = 0 ; i < registered.size() ; ++i){
		Actor actor = registered.get(i);
		actors.add(actor);
	}
	registered.clear();
	
	//remove unregistered actors
	for (int i = 0 ; i < unregistered.size() ; ++i){
		Actor actor = unregistered.get(i);
		actors.remove(actor);
	}
	unregistered.clear();
	
	
	}
	
	

    @Override
    public Loader getLoader() {
        return loader;
    }
    
    @Override
    public void register(Actor actor){
    	registered.add(actor);
    }
    
    @Override
    public void unregistered(Actor actor){
    	unregistered.add(actor);
    }
    
}
