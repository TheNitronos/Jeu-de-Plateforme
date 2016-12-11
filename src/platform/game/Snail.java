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
	private double majCos;
	
	public Snail(Vector pos, Vector velo){
		position = pos;
		velocity = velo;
		cooldown = 3.0;
		SIZE = 0.5;
		maj = cooldown/4;
		majCos = 2* Math.PI;
		
		
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
		if (countdown > 0.0){
			name += "shell";
		}
		else{
			if (maj > cooldown/8){
				name += "2";
			}
			else{
				name += "1";
			}
		}
		
		output.drawSprite(getSprite(name), getBox());
	}
	
	@Override
	public Box getBox(){
		return new Box(position.add(new Vector(0.0, 0.1)), SIZE, SIZE);
	}
	
	@Override
	public void update(Input input){
		maj -= input.getDeltaTime();
		if (maj < 0.0){
			maj = cooldown/4;
		}
		countdown -= input.getDeltaTime();
		if (countdown < -1){
			countdown = -1;
		}
		
		majCos -= input.getDeltaTime();
		if(majCos < 0.0){
			majCos = 2 * Math.PI;
		}
		
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		
		if (countdown <= 0.0){
			velocity = velocity.add(acceleration.mul(delta));
			position = position.add(velocity.mul(delta));
		}
		
	}
	
	@Override
	public void interact(Actor other) {
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
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		switch (type){
		 case FIRE:
			 countdown = cooldown;
			 return true;
		default:
			return super.hurt(instigator, type, amount, location);
			 
		}
	}
}
