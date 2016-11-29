/*
 *	Author:      Samuel Chassot
 *	Date:        28 nov. 2016
 */


package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;


public class Spike extends Actor{
	private final double SIZE = 1.0;
	private Vector position;
	
	
	public Spike(Vector pos){
		position = pos;
	}
	
	@Override
	public int getPriority(){
		return 333;
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE/2);
	}
	
	@Override
	public void draw(Input input, Output output){
		Sprite sprite = this.getSprite("spikes");
		if (sprite != null){
			output.drawSprite(sprite, getBox());
		}
	}
	
	@Override
	public void interact(Actor other){
		if(other.getVelocityY() < -1 && getBox().isColliding(other.getBox())){
			other.hurt(this, Damage.PHYSICAL, 5.0 , position);
		}
		
	}
}
