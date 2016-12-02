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
	
	private World world;

	//pour evoluer au cours du temps	
	public void update(Input input) {}
	
	//pour evoluer avant la physique et apres le dessin
	public void preUpdate(Input input) {}
	
	public void postUpdate(Input input) {}
	
	//pour etre dessiné	
	public void draw(Input input, Output output) {}
	
	public abstract int getPriority();
	
	
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		return false;
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
		if(getPriority() < other.getPriority()){
			return 1;
		}
		if(getPriority() > other.getPriority()){
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
	
	public double getVelocityY(){
		return 0.0;
	}


	
}
