/*
 *	Author:      Samuel Chassot
 *	Date:        24 nov. 2016
 */


package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;

import platform.util.Sprite;
import platform.util.Output;

public class Fireball extends Actor{
	
	private final double SIZE = 0.4;
	private Vector velocity;
	private Vector position;

	
	public Fireball(Vector vel, Vector pos){
		super(666);
		if(vel == null || pos == null){
			throw new NullPointerException();
		}
		
		velocity = vel;
		position = pos;
		

	}
	
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
		
	}
	public Vector getVelocity(){
		return velocity;
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity);
	}
	
	public void draw(Input in, Output out){
		Sprite sprite =getSprite("fireball");
		if (sprite != null){
			super.draw(in, out);
			out.drawSprite(sprite, getBox(), in.getTime());
		}
	
	}
	
}
