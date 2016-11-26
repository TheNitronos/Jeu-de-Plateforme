/*
 *	Author:      Samuel Chassot
 *	Date:        26 nov. 2016
 */


package platform.game;

import platform.util.Box;
import platform.game.Actor;
import platform.util.Vector;


public class Limits extends Actor {
	
	private Box box;
	
	public Limits(Box box){
		this.box = box;
	}
	
	@Override
	public void interact(Actor other){
		
		if (!getBox().isColliding(other.getBox())){
			
			super.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
			other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
		}
	}
	
	@Override
	public Box getBox(){
		return box;
	}
	
	@Override
	public int getPriority(){
		return 66666;
	}
}
