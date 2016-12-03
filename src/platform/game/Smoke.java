/*
 *	Author:      Samuel Chassot
 *	Date:        1 déc. 2016
 */


package platform.game;

import platform.util.Box;
import platform.game.Color;
import platform.util.Input;
import platform.util.Output;
import platform.game.Player;
import platform.util.Vector;
import platform.util.Box;

public class Smoke extends Actor {
	private Vector position;
	private final double COOLDOWN = 0.5;
	private  double cooldown;
	private double size;
	
	public Smoke(Vector pos, double size){
		this.size = size;
		position = pos;
		cooldown = COOLDOWN;
	}
	
	@Override
	public int getPriority(){
		return 1;
	}
	
	@Override
	public void update(Input input){
		cooldown -= input.getDeltaTime();
		if(cooldown < 0.0){
			getWorld().unregister(this);
		}
	}
	@Override
	public Box getBox(){
		return new Box(position, size, size);
	}
	
	@Override
	public void draw(Input input, Output output){
		String name;
		if (cooldown >= COOLDOWN * 2/3){
			name = "smoke.gray.1";
		}
		else if(cooldown >= COOLDOWN/3){
			name = "smoke.gray.2";
		}
		else{
			name = "smoke.gray.3";
		}
		
		output.drawSprite(getSprite(name), getBox());
	}
}
