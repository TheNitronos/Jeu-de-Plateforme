/*
 *	Author:      Samuel Chassot
 *	Date:        9 déc. 2016
 */


package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Output;
import platform.util.Input;

public class Snail extends Actor{
	private Vector position;
	private final double cooldown;
	private double countdown;
	private Vector velocity;
	private final double SIZE;
	
	public Snail(Vector pos, Vector velo){
		position = pos;
		velocity = velo;
		cooldown = 3.0;
		SIZE = 0.5;
		
		
	}
	
	@Override
	public int getPriority(){
		return 20;
	}
	
	@Override
	public void draw(Input input, Output output){
		String name = "snail.";
		if (velocity.getX() >= 0.0){
			name += "right.";
		}
		else{
			name += "left.";
		}
		if (cooldown > 0.0){
			name += "shell.";
		}
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
}
