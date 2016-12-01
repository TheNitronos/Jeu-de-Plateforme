/*
 *	Author:      Samuel Chassot
 *	Date:        1 déc. 2016
 */


package platform.game;

import platform.game.Block;
import platform.game.Signal;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;
import platform.game.level.*;



public class Exit extends Actor {
	private Level level;
	private Signal signal;
	private Vector position;
	private final double SIZE = 1.0;
	
	public Exit(Vector pos, Signal signal, Level lvl){
		this.signal = signal;
		position = pos;
		level = lvl;
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
	@Override
	public int getPriority(){
		return 10;
	}
	
	public void interact(Actor other){
		super.interact(other);
		if(getBox().isColliding(other.getBox()) && other.getClass().getName() == "platform.game.Player"){
			getWorld().setNextLevel(level);
			getWorld().nextLevel();
		}
	}
}
