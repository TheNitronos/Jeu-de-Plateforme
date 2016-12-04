package platform.game;

import platform.game.level.Level;
import java.util.ArrayList;
import platform.util.Box;

import platform.util.Input;
import platform.util.Loader;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
import platform.util.View;
import platform.util.SortedCollection;
import platform.game.level.BasicInteract;

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
    private Level next;
    private boolean transition;
  
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
        
        next = Level.createDefaultLevel();
        transition = true;
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
		
	double factor = 0.1;
	
	currentCenter = currentCenter.mul(1.0 - factor).add(expectedCenter.mul(factor));
	currentRadius = currentRadius * (1.0 - factor) + expectedRadius*factor;
	    
	View view = new View(input, output);
	view.setTarget(currentCenter, currentRadius);
	
	//apply update before physics
	for (Actor a : actors){
		a.preUpdate(view);
	}
	
	//interact each actor who need with others
	for (Actor actor : actors) {
		for (Actor other : actors) {
			if (actor.getPriority() > other.getPriority()) {
				actor.interact(other);
			}
		}
	}

	//apply update
	for (Actor a : actors) {
		a.update(view);
	}
	
	//Draw everything
	for (Actor a : actors.descending()) {
		a.draw(view, view);
	}
	
	//apply update after drawing
	for (Actor a : actors) {
		a.postUpdate(view);
	}
	
	//add registered actors
	for (int i = 0 ; i < registered.size() ; ++i) {
		Actor actor = registered.get(i);
		
		if (!actors.contains(actor)) {
			actor.register(this);
			actors.add(actor);
		}
	}
	
	registered.clear();
	
	//remove unregistered actors
	for (int i = 0 ; i < unregistered.size() ; ++i) {
		Actor actor = unregistered.get(i);
		actor.unregister();
		actors.remove(actor);
		System.out.println("oilllle");
	}
	
	unregistered.clear();
	
	//si un acteur a mis transition a true pour demander de changer de niveau
	if (transition) {
		currentCenter = Vector.ZERO;
        currentRadius = 10.0;
        
        expectedCenter = Vector.ZERO;
        expectedRadius = 10.0;
        
		if(next == null) {
			next = Level.createDefaultLevel();
		}
		
		//si un acteur a appelé setNextLevel, next ne sera pas null
		Level level = next;
		transition = false;
		next = null;
		actors.clear();
		registered.clear();
		
		//tout est désenregistrer meme le level precedent
		unregistered.clear();
		register(level);
	}
}

	@Override
    public Loader getLoader() {
        return loader;
    }
    
    @Override
    public void register(Actor actor) {
    	registered.add(actor);
    }
    
    @Override
    public void unregister(Actor actor) {
    	unregistered.add(actor);
    }
    
    @Override
    public void setNextLevel(Level lvl) {
    	next = lvl;
    }
    
    @Override
    public void nextLevel() {
    	transition = true;
    }
    
    @Override
    public int hurt(Box area, Actor instigator, Damage type, double amount, Vector location) {
    	int victims = 0;
    	
    	for (Actor actor : actors){
    		if(area.isColliding(actor.getBox()) && instigator != actor){
    			if (actor.hurt(instigator, type, amount, location)){
    				++victims;
    			}
    		}	
    	}
    	
    	return victims;
    }
    
}
