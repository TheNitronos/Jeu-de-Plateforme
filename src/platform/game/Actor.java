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
	
	//pour evoluer au cours du temps	
	public void update(Input input) {}
	
	//pour etre dessiné	
	public void draw(Input input, Output output) {}
	
	public int getPriority(){
		return priority;
	}
	
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
	
}
