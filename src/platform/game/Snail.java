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
	private double maj;
	public Snail(Vector pos, Vector velo){
		position = pos;
		velocity = velo;
		cooldown = 3.0;
		SIZE = 0.5;
		maj = cooldown/4;
		
		
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
		if (maj > cooldown/8){
			name += "2";
		}
		else{
			name += "1";
		}
		
		
		output.drawSprite(getSprite(name), getBox());
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
	@Override
	public void update(Input input){
		maj -= input.getDeltaTime();
		if (maj < 0.0){
			maj = cooldown/4;
		}
		
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
		
		
	}
	@Override
	public void interact(Actor other){
		super.interact(other);
				
				if (other.isSolid()) {
					Vector delta = other.getBox().getCollision(getBox());
					
					if (delta != null) {
						position = position.add(delta);
						
						if (delta.getX() != 0.0) {
							velocity = new Vector(-1.0 * velocity.getX(), velocity.getY());
						}
						
						if (delta.getY() != 0.0) {
							velocity = new Vector(velocity.getX(), 0.0);
						}
					}
				}
				
			}
		

	
}
