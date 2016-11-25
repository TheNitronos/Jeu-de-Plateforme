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

public class Fireball extends Actor {
	
	private final double SIZE = 0.4;
	private Vector velocity;
	private Vector position;
	private Sprite sprite;

	
	public Fireball(Vector vel, Vector pos){
		
		if(vel == null || pos == null){
			throw new NullPointerException();
		}
		
		velocity = vel;
		position = pos;
	}
	
	@Override
	public int getPriority(){
		return 666;
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
		position = position.add(velocity.mul(delta));
	}
	
	@Override
	public void draw(Input in, Output out){
		super.draw(in, out);
		
		if (sprite != null) {			;
			out.drawSprite(sprite, getBox(), in.getTime());
		}
	}
	
	@Override
	public void register(World world){
		super.register(world);
		sprite = super.getSprite("fireball");
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		if (other.isSolid()){
			Vector delta = other.getBox().getCollision(position);
			if (delta != null){
				position = position.add(delta);
				velocity = velocity.mirrored(delta);
			}
		}
		if (other.getBox().isColliding(getBox())){
			if(other.hurt(this, Damage.FIRE, 1.0, getPosition())){
				//la boule disparait si elle a brule qqn ou qqch
				getWorld().unregister(this);
			}
		}
	}
}
