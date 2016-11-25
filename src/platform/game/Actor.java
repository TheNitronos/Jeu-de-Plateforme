package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;

/**
 * Base class of all simulated actors, attached to a world.
 */

public abstract class Actor implements Comparable<Actor> {
	
	private int priority;
	private World world;
	
	public Actor(int p){
		priority = p;
	}
	
	//pour evoluer au cours du temps	
	public void update(Input input) {}
	
	//pour evoluer avant la physique et apres le dessin
	public void preUpdate(Input input) {}
	
	public void postUpdate(Input input) {}
	
	//pour etre dessiné	
	public void draw(Input input, Output output) {}
	
	public int getPriority(){
		return priority;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public Box getBox(){
		return null;
	}
	
	public Vector getPosition(){
		Box box = getBox();
		if (box == null) {
			return null;
		}
		return box.getCenter();
	}
	
	public void interact(Actor other) {}
	
	@Override
	public int compareTo(Actor other){
		if(priority < other.getPriority()){
			return 1;
		}
		if(priority > other.getPriority()){
			return -1;
		}
		else{
			return 0;
		}
	}
	
	public void register(World nWorld){
		world = nWorld;
	}
	
	public void unregister(){
		world = null;
	}
	
	protected World getWorld(){
		return world;
	}
	
	public Sprite getSprite(String name){
		return world.getLoader().getSprite(name);
	}
	
	//public abstract void initSprite();
}
