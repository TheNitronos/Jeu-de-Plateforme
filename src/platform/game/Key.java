/*
 *	Author:      Samuel Chassot
 *	Date:        30 nov. 2016
 */


package platform.game;


import platform.util.Vector;
import platform.util.Box;
import platform.game.Color;
import platform.util.Input;
import platform.util.Output;
import platform.game.Player;



public class Key extends Actor implements Signal{
	
	private final double SIZE = 1.0;
	private Vector position;
	private boolean taken;
	private Color color;
	
	public Key(Vector pos, Color col){
		position = pos;
		color = col;
		taken = false;
	}
	
	@Override
	public void draw(Input input, Output output){
		if (!taken){
			super.draw(input, output);
			String name;
			switch(color){
			
				case GREEN:
					name = "key.green";
					break;
				case YELLOW:
					name = "key.yellow";
					break;
				case BLUE:
					name = "key.blue";
					break;
				case RED:
					name = "key.red";
					break;
				default:
					name = "key.green";
			}
			
			output.drawSprite(getSprite(name), getBox());
		}
	
	}
	@Override
	public Box getBox(){
		if (!taken){
			return new Box(position, SIZE, SIZE);
		}
		else{
			return null;
		}
	}
	
	@Override
	public int getPriority(){
		return 122;
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		if (other.getClass().getName() == "platform.game.Player" && other.getBox().isColliding(getBox())){
			taken = true;
			getWorld().unregister(this);
		}
	}
	
	@Override 
	public boolean isActive(){
		return taken;
	}
	
	@Override
	public void update(Input input){
		if (taken){
			getWorld().unregister(this);
		}
	}
}
